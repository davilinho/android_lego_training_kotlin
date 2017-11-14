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
import com.wtransnet.training.kotlin.trainingkotlinapp.util.app
import com.wtransnet.training.kotlin.trainingkotlinapp.util.navigateTo
import com.wtransnet.training.kotlin.trainingkotlinapp.util.snack
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: AppCompatActivity(), IMainView {

    @Inject lateinit var presenter: MainPresenter

    private val logger = Logger.instance
    private val adapter = LegoAdapter(emptyList()) { onClickListener(it) }
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
            data = list
            notifyDataSetChanged()
        }
        logger.info<MainActivity>("Recuperados un total de ${list.size} legos!")
        snack("Recuperados un total de ${list.size} legos!")
        callback()
    }

    override fun navigateToDetail(id: Int) {
        navigateTo<DetailActivity>(id.toString())
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

    private fun onClickListener(id: Int) {
        presenter.loadDetail(id)
    }
}
