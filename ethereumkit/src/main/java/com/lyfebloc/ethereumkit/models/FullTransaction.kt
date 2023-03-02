package com.lyfebloc.ethereumkit.models

import com.lyfebloc.ethereumkit.decorations.TransactionDecoration

class FullTransaction(
    val transaction: Transaction,
    val decoration: TransactionDecoration
)
