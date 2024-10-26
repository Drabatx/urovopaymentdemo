package com.drabatx.urovopaymentapp.di

import com.drabatx.urovopaymentapp.data.model.pos2.models.PosInputDatas
import com.drabatx.urovopaymentapp.domain.repository.MyEmvListener
import com.drabatx.urovopaymentapp.presentation.view.factory.MyEmvListenerFactory
import com.urovo.i9000s.api.emv.EmvNfcKernelApi
import com.urovo.sdk.beeper.BeeperImpl
import com.urovo.sdk.led.LEDDriverImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UrovoModule {

    @Provides
    @Singleton
    fun provideKernelApi() =  EmvNfcKernelApi.getInstance()

    @Provides
    @Singleton
    fun provideIBeeper() = BeeperImpl.getInstance()

    @Provides
    @Singleton
    fun provideILed() = LEDDriverImpl.getInstance()
}

@Module
@InstallIn(ViewModelComponent::class)
object CardReaderModule {

    @Provides
    fun provideMyEmvListenerFactory(
        mKernelApi: EmvNfcKernelApi,
        iBeeper: BeeperImpl,
        iLed: LEDDriverImpl
    ): MyEmvListenerFactory {
        return MyEmvListenerFactory(mKernelApi, iBeeper, iLed)
    }
}