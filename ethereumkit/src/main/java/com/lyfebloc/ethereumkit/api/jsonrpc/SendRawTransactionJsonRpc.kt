package com.lyfebloc.ethereumkit.api.jsonrpc

class SendRawTransactionJsonRpc(
        @Transient val signedTransaction: ByteArray
) : DataJsonRpc(
        method = "eth_sendRawTransaction",
        params = listOf(signedTransaction)
)
