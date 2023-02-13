package com.ncgr.maqsaf.data.repository

import android.util.Log
import com.ncgr.maqsaf.data.model.ApiError
import com.ncgr.maqsaf.data.remote.api.ticketApi.TicketApi
import com.ncgr.maqsaf.data.remote.api.ticketApi.body.AddTicket
import com.ncgr.maqsaf.domain.ticket.model.Ticket
import com.ncgr.maqsaf.domain.ticket.repository.TicketRepository
import com.ncgr.maqsaf.presentation.common.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class TicketRepositoryImp(
    private val ticketApi: TicketApi,
) : TicketRepository {
    override fun getMyTickets(uid: String): Flow<Resource<List<Ticket>>> = flow {
        emit(Resource.Loading())
        try {
            val response = ticketApi.getMyTickets("eq.$uid")
            if (response.isSuccessful) {
                val ticketList = response.body()!!.map { it.toTicket() }
                emit(Resource.Success(ticketList))
            } else {
                emit(Resource.Error(ApiError(response.code(), response.message())))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(ApiError(e.code(), e.message())))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    ApiError(
                        e.hashCode(),
                        "Please check your internet connection and try again"
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Error(ApiError(0, "Something went wrong")))
        }
    }

    override fun addTicket(addTicket: AddTicket): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val response = ticketApi.addTicket(addTicket)
            Log.d("TEST", response.toString())
            if (response.isSuccessful) {
                emit(Resource.Success(true))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(ApiError(e.code(), e.message())))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    ApiError(
                        e.hashCode(),
                        "Please check your internet connection and try again"
                    )
                )
            )
        } catch (e: Exception) {
            emit(Resource.Error(ApiError(0, "Something went wrong")))
        }
    }
}