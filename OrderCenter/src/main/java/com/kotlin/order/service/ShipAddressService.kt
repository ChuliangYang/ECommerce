package com.kotlin.order.service

import com.kotlin.order.data.protocol.ShipAddress
import io.reactivex.Observable

interface ShipAddressService {

    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean>

    fun getShipAddressList(): Observable<MutableList<ShipAddress>?>

    fun editShipAddress(address:ShipAddress): Observable<Boolean>

    fun deleteShipAddress(id: Int): Observable<Boolean>

}
