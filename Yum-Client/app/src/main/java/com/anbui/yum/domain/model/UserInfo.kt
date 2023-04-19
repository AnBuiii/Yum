package com.anbui.yum.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
  val userId: String = "",
  val name: String = "",
  val title: String = "",
  val id: String = "",
)