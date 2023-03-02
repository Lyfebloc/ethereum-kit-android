package com.lyfebloc.ethereumkit.api.jsonrpc

import com.lyfebloc.ethereumkit.models.Address
import com.lyfebloc.ethereumkit.models.DefaultBlockParameter

class GetTransactionCountJsonRpc(
        @Transient val address: Address,
        @Transient val defaultBlockParameter: DefaultBlockParameter
) : LongJsonRpc(
        method = "eth_getTransactionCount",
        params = listOf(address, defaultBlockParameter)
)
