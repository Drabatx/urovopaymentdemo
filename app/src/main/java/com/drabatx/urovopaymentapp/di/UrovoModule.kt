package com.drabatx.urovopaymentapp.di

import android.content.Context
import com.drabatx.urovopaymentapp.domain.repository.MyEmvListener
import com.urovo.i9000s.api.emv.EmvNfcKernelApi
import com.urovo.sdk.beeper.BeeperImpl
import com.urovo.sdk.insertcard.InsertCardHandlerImpl
import com.urovo.sdk.led.LEDDriverImpl
import com.urovo.sdk.magcard.MagCardReaderImpl
import com.urovo.sdk.pinpad.PinPadProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UrovoModule {
    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideEmvNfcKernelApi(): EmvNfcKernelApi {
        return EmvNfcKernelApi.getInstance()
    }

    @Provides
    @Singleton
    fun provideIBeeper(): BeeperImpl = BeeperImpl.getInstance()

    @Provides
    @Singleton
    fun provideILed(): LEDDriverImpl = LEDDriverImpl.getInstance()


    @Provides
    @Singleton
    fun provideMagCardReaderImpl(): MagCardReaderImpl = MagCardReaderImpl.getInstance()

    @Provides
    @Singleton
    fun provideInsertCardHandlerImpl() = InsertCardHandlerImpl.getInstance()

    @Provides
    @Singleton
    fun providePinPadProviderImpl() = PinPadProviderImpl.getInstance()

}

@Module
@InstallIn(ViewModelComponent::class)
object CardReaderModule {

    @Provides
    fun provideMyEmvListener(
        mKernelApi: EmvNfcKernelApi,
        iBeeper: BeeperImpl,
        iLed: LEDDriverImpl
    ): MyEmvListener {
        return MyEmvListener(mKernelApi, iBeeper, iLed)
    }

}