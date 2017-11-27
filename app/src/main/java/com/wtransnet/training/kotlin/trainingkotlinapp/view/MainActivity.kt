package com.wtransnet.training.kotlin.trainingkotlinapp.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.wtransnet.training.kotlin.trainingkotlinapp.R
import com.wtransnet.training.kotlin.trainingkotlinapp.log.Logger
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.modules.MainModule
import com.wtransnet.training.kotlin.trainingkotlinapp.presenter.MainPresenter
import com.wtransnet.training.kotlin.trainingkotlinapp.util.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: AppCompatActivity(), IMainView {

    @Inject lateinit var presenter: MainPresenter

    private val logger = Logger.instance
    private val adapter = LegoAdapter(mutableListOf(), { onClickDetailListener(it) }, { onClickDeleteListener(it) })
    private val component by lazy { app.component.plus(MainModule(this)) }
    private lateinit var currentFilter: LegoItem.LegoType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logger.debug<MainActivity>("Iniciando onCreate")

        inject()
        initSwipe()
        initAdapter()
        loadImages()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.filter_all -> loadImages()
            R.id.filter_with_hat -> filterImagesWithHat()
            R.id.filter_without_hat -> filterImagesWithoutHat()
        }
        return super.onOptionsItemSelected(item)
    }

    override val context: Context
        get() = this

    override fun showLoading(callback: () -> Unit) {
        swipe.isRefreshing = true
        callback()
    }

    override fun hideLoading() {
        swipe.isRefreshing = false
    }

    override fun showImages(list: List<LegoItem>, callback: () -> Unit) {
        with(adapter) {
            data = list.toMutableList()
            notifyDataSetChanged()
        }
        logger.info<MainActivity>("Recuperados un total de ${list.size} legos!")
        snack("Recuperados un total de ${list.size} legos!")
        callback()
    }

    override fun navigateToDetail(id: Int) {
        navigateTo<DetailActivity>(id.toString())
    }

    override fun deleteItemFeedback(item: LegoItem) {
        snack("Eliminado con éxito el item número ${item.id}")
    }

    override fun showError(message: String) {
        logger.error<MainActivity>(message)
        snack(message)
        hideLoading()
    }

    override fun showOfflineMessage() {
        logger.warning<MainActivity>("Perdida la conexión, por favor espere...")
        snack("Perdida la conexión, por favor espere...")
        hideLoading()
    }

    private fun initSwipe() {
        swipe.setOnRefreshListener {
            when (currentFilter) {
                LegoItem.LegoType.ALL -> loadImages()
                LegoItem.LegoType.WITH_HAT -> filterImagesWithHat()
                LegoItem.LegoType.WITHOUT_HAT -> filterImagesWithoutHat()
            }
        }
    }

    private fun inject() {
        component.inject(this)
    }

    private fun initAdapter() {
        recycler.adapter = adapter
    }

    private fun loadImages() {
        currentFilter = LegoItem.LegoType.ALL
        presenter.loadImages()
    }

    private fun filterImagesWithHat() {
        currentFilter = LegoItem.LegoType.WITH_HAT
        presenter.filterImages(LegoItem.LegoType.WITH_HAT)
    }

    private fun filterImagesWithoutHat() {
        currentFilter = LegoItem.LegoType.WITHOUT_HAT
        presenter.filterImages(LegoItem.LegoType.WITHOUT_HAT)
    }

    private fun onClickDetailListener(id: Int) {
        cancelSnack("Abriendo detalle...", {
            CancelSnackCallback { presenter.loadDetail(id) }
        }, {
            snack("Petición cancelada")
            hideLoading()
        })
    }

    private fun onClickDeleteListener(item: LegoItem) {
        undoSnack("Eliminando item número ${item.id}, por favor espere...", {
            UndoSnackCallback({ hideRow(item) }, { deleteRow(item) })
        }, {
            snack("Borrado deshecho")
            loadImages()
        })
    }

    private fun hideRow(item: LegoItem) {
        with(adapter) {
            val position = data.indexOf(item)
            data.remove(item)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, data.size)
        }
    }

    private fun deleteRow(item: LegoItem) {
        presenter.deleteLegoItem(item)
    }
}
