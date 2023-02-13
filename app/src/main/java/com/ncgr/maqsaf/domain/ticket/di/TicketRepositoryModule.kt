package com.ncgr.maqsaf.domain.ticket.di

import com.ncgr.maqsaf.data.remote.api.ticketApi.TicketApi
import com.ncgr.maqsaf.data.repository.TicketRepositoryImp
import com.ncgr.maqsaf.domain.ticket.repository.TicketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TicketRepositoryModule {

    @Singleton
    @Provides
    fun provideTicketRepository(
        ticketApi: TicketApi
    ): TicketRepository {
        return TicketRepositoryImp(ticketApi)
    }
}