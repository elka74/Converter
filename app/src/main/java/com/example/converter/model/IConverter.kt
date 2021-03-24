package com.example.converter.model



import io.reactivex.rxjava3.core.Completable

interface IConverter {

    fun convert(image: IImage): Completable
}
