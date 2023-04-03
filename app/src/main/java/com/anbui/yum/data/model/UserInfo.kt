package com.anbui.yum.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
  val userId: String = "",
  val name: String = "",
  val title: String = "",
  val id: String = "",
)
