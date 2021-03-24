package com.example.converter.presenter

import com.example.converter.model.IConverter
import com.example.converter.model.IImage
import com.example.converter.view.ConverterView
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter

class ConvertPresenter(val uiScheduler: Scheduler, val converter:IConverter): MvpPresenter<ConverterView>() {

    fun click(){
        viewState.pickImage()
    }
    var convertDisposable:Disposable? = null

    fun selected(image: IImage){
        viewState.showInProgress()
        convertDisposable = converter.convert(image)
            .observeOn(uiScheduler) // результат возвращаем в ui
            .subscribe ({
                viewState.hideInProgress() // закрываем диалог
                viewState.showSuccess() // успешная конвертация
            }, {
                viewState.hideInProgress()
                viewState.showError() // выводим ошибку
            })
    }

}