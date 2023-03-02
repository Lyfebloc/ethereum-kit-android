package com.lyfebloc.ethereumkit.api.jsonrpc

import com.google.gson.reflect.TypeToken
import com.lyfebloc.ethereumkit.api.jsonrpc.models.RpcTransactionReceipt
import java.lang.reflect.Type

class GetTransactionReceiptJsonRpc(
        @Transient val transactionHash: ByteArray
) : JsonRpc<RpcTransactionReceipt>(
        method = "eth_getTransactionReceipt",
        params = listOf(transactionHash)
) {
    @Transient
    override val typeOfResult: Type = object : TypeToken<RpcTransactionReceipt>() {}.type
}
