package com.ncgr.maqsaf.domain.ticket.di

import com.ncgr.maqsaf.domain.ticket.repository.TicketRepository
import com.ncgr.maqsaf.domain.ticket.usecase.AddTicketUseCase
import com.ncgr.maqsaf.domain.ticket.usecase.GetMyTicketsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TicketUseCaseModule {

    @Singleton
    @Provides
    fun provideGetMyTicketsUseCase(
        ticketRepository: TicketRepository
    ): GetMyTicketsUseCase {
        return GetMyTicketsUseCase(ticketRepository)
    }

    @Singleton
    @Provides
    fun provideAddTicketUseCase(
        ticketRepository: TicketRepository
    ): AddTicketUseCase {
        return AddTicketUseCase(ticketRepository)
    }
}