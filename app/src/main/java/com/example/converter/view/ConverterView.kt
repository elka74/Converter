package com.example.converter.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ConverterView: MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun pickImage()

    fun showInProgress()
    fun hideInProgress()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showSuccess()
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError()

}