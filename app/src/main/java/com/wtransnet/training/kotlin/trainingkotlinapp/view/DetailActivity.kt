package com.wtransnet.training.kotlin.trainingkotlinapp.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.wtransnet.training.kotlin.trainingkotlinapp.R
import com.wtransnet.training.kotlin.trainingkotlinapp.log.Logger
import com.wtransnet.training.kotlin.trainingkotlinapp.model.LegoItem
import com.wtransnet.training.kotlin.trainingkotlinapp.modules.DetailModule
import com.wtransnet.training.kotlin.trainingkotlinapp.presenter.DetailPresenter
import com.wtransnet.training.kotlin.trainingkotlinapp.util.*
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

/**
* Created by davidmartin on 24/10/17.
*/
class DetailActivity: AppCompatActivity(), IDetailView {

    @Inject lateinit var presenter: DetailPresenter

    private val logger = Logger.instance
    private val component by lazy { app.component.plus(DetailModule(this)) }
    private lateinit var item: LegoItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        inject()
        loadDetail(intent)
        bindButton()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> NavUtils.navigateUpFromSameTask(this)
        }
        return super.onOptionsItemSelected(item)
    }

    override val context: Context
        get() = this

    override fun showDetail(item: LegoItem) {
        fillItem(item)
        loadItemData()
    }

    override fun showInfoDetail(message: String) {
        toast(message)
    }

    override fun showLoading(callback: () -> Unit) { }

    override fun hideLoading() { }

    override fun showError(message: String) {
        logger.error<MainActivity>(message)
        snack(message)
    }

    override fun showOfflineMessage() {
        logger.warning<MainActivity>("Perdida la conexión, por favor espere...")
        snack("Perdida la conexión, por favor espere...")
    }

    private fun loadDetail(intent: Intent) {
        intent.getStringExtra(DATA).let {
            presenter.loadDetail(it.toInt())
        }
    }

    private fun inject() {
        component.inject(this)
    }

    private fun bindButton() {
        detail_button.setOnClickListener { presenter.openInfoDetail(item) }
    }

    private fun fillItem(item: LegoItem) { this.item = item }

    private fun loadItemData() {
        supportActionBar?.title = item.text
        detail_image.loadUrl(item.url)
        detail_type.text = mapLegoType(item.type)
    }
}