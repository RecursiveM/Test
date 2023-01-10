package com.ncgr.maqsaf.presentation.serviceProvider.viewModel

import androidx.lifecycle.ViewModel
import com.ncgr.maqsaf.domain.menu.model.Item
import com.ncgr.maqsaf.domain.order.model.Order
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class ServiceProviderViewModel : ViewModel() {

    private val _orderList = MutableStateFlow(
        listOf(
            Order("1", 1, "Red", false),
            Order("2", 2, "Green", false),
            Order("3", 3, "Blue", false),
            Order("4", 4, "Yellow", false),
        )
    )
    val orderList = _orderList.asStateFlow()

    private val _itemList = MutableStateFlow(
        listOf(
            Item(
                "1",
                "https://kiwknwdhkizntbquwcem.supabase.co/storage/v1/object/sign/images/tea-cup-tea-svgrepo-com.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJpbWFnZXMvdGVhLWN1cC10ZWEtc3ZncmVwby1jb20ucG5nIiwidHJhbnNmb3JtYXRpb25zIjoiIiwiaWF0IjoxNjczMzQ3NzMwLCJleHAiOjE5ODg3MDc3MzB9.nnCXBdpUvJktSehlRQz3_hNBH4MJzhMdaQjGFF9nxMQ",
                "tea"
            ),
            Item(
                "2",
                "https://kiwknwdhkizntbquwcem.supabase.co/storage/v1/object/sign/images/coffee-cup-coffee-svgrepo-com.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJpbWFnZXMvY29mZmVlLWN1cC1jb2ZmZWUtc3ZncmVwby1jb20ucG5nIiwidHJhbnNmb3JtYXRpb25zIjoiIiwiaWF0IjoxNjczMzQ3NTk3LCJleHAiOjE5ODg3MDc1OTd9.0_inO7zSQ873Q_qXByEn01G5cKXU751tm_-YOfcHk-s",
                "coffee"
            ),
            Item(
                "3",
                "https://kiwknwdhkizntbquwcem.supabase.co/storage/v1/object/sign/images/water-bottle-svgrepo-com.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJpbWFnZXMvd2F0ZXItYm90dGxlLXN2Z3JlcG8tY29tLnBuZyIsInRyYW5zZm9ybWF0aW9ucyI6IiIsImlhdCI6MTY3MzM0Nzc0MCwiZXhwIjoxOTg4NzA3NzQwfQ.h6redDVuKP5wQDvOLfRSzeJoU5AaPSyeY3HdIh551vc",
                "water"
            ),
            Item(
                "4",
                "https://kiwknwdhkizntbquwcem.supabase.co/storage/v1/object/sign/images/water-bottle-svgrepo-com.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1cmwiOiJpbWFnZXMvd2F0ZXItYm90dGxlLXN2Z3JlcG8tY29tLnBuZyIsInRyYW5zZm9ybWF0aW9ucyI6IiIsImlhdCI6MTY3MzM0Nzc0MCwiZXhwIjoxOTg4NzA3NzQwfQ.h6redDVuKP5wQDvOLfRSzeJoU5AaPSyeY3HdIh551vc",
                "water"
            ),
        )
    )
    val itemList = _itemList.asStateFlow()
}