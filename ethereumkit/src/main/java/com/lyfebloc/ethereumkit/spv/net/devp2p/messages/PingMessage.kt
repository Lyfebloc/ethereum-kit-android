package com.lyfebloc.ethereumkit.spv.net.devp2p.messages

import com.lyfebloc.ethereumkit.core.hexStringToByteArray
import com.lyfebloc.ethereumkit.spv.net.IInMessage

class PingMessage() : IInMessage {

    constructor(payload: ByteArray) : this()

    override fun toString(): String {
        return "Ping"
    }

    companion object {
        val payload = "C0".hexStringToByteArray()
    }
}
