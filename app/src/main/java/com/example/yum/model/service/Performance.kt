package com.example.yum.model.service

import com.google.firebase.perf.ktx.trace
import com.google.firebase.perf.metrics.Trace

// trace a block with firebase performance
inline fun <T> trace(name: String, block: Trace.() -> T): T = Trace.create(name).trace(block)