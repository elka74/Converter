package com.example.converter.ui


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.converter.model.IConverter
import com.example.converter.model.IImage
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream

class Converter(val context: Context?) : IConverter {
    override fun convert(image: IImage) =
        Completable.fromAction {//Completable подходит для случаев, когда нам не нужно получать значения, fromAction заменяет fromCallable, т.к. не нужен onNext
            context?.let { context->
                try {
                    Thread.sleep(5000)
                } catch (e: InterruptedException) {
                    return@let
                }
            }

            val bmp = BitmapFactory.decodeByteArray(
                image.picture,
                0,
                image.picture.size
            ) //декодируем неизменяемое растровое изображение из указанного массива байтов.

            val file = File(context?.getExternalFilesDir(null), "cat_converted.png") // возвращаем обсолютный путь к катаклогу и название файла
            val transfer = FileOutputStream(file) // записываем поток необработанных байтов
            bmp.compress(
                Bitmap.CompressFormat.PNG,
                100,
                transfer
            ) // производим сжатие в формат png, задаем качество и возвращаем в файл
        }.subscribeOn(Schedulers.io()) // выполняем все в фоновом потоке

}