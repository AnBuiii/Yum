package com.anbui.yum.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val title: String = "",
    val subTitle: String = "",
    val note: String = "",
    val ratings: Float = 0f,
    val servings: Int = 0,
    val caloriesPerServing: Int = 0,
    val totalTimeInMinute: Int = 0,
    val imageUrl: String = "https://images.unsplash.com/photo-1680199994489-22b5f4ccc620?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw1fHx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=500&q=60",
    val ingredients: List<IngredientDetail>? = null,
    val steps: List<String>? = null,
    val userId: String = "",
    val id: String = "",
)

val recipes = listOf(
    Recipe(
        title = "This is something very delicious i think :))",
        subTitle = "Yummy",
        note = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque et dolor sem. Integer sit amet fermentum dui. Suspendisse non eros accumsan nunc interdum mollis. Aenean a massa et augue commodo gravida. Sed bibendum massa turpis, vitae tristique quam eleifend scelerisque. Sed nec orci nec dolor pharetra aliquam. In accumsan arcu orci, ut varius tellus bibendum ac. Nam vel justo cursus, ultrices nisl ut, mattis ex. Curabitur auctor at orci non scelerisque.\n" +
                "\n" +
                "Ut sagittis massa ut massa consectetur, et ultrices quam eleifend. Maecenas rutrum lobortis volutpat. Morbi at dui ligula. Etiam faucibus id justo quis malesuada. Morbi laoreet neque sit amet consequat ultrices. Vestibulum ut mi ut sem vestibulum scelerisque. Vestibulum elementum eros magna, vel commodo justo lobortis et. Suspendisse sit amet convallis felis. Aliquam placerat enim id erat scelerisque, sed vulputate nisl iaculis. Aenean accumsan nec mi sit amet fermentum. Aliquam vel dolor vitae erat vestibulum semper. Mauris fringilla rutrum enim eu euismod.\n" +
                "\n" +
                "Aenean vitae tortor fringilla, vulputate neque nec, congue magna. Integer a egestas neque. Suspendisse mattis aliquam dui sit amet vestibulum. Ut vulputate ac urna vitae lobortis. Pellentesque placerat est id turpis placerat convallis. Curabitur iaculis pellentesque odio id cursus. Aliquam malesuada dignissim maximus. Sed euismod finibus eros vel egestas. Etiam auctor metus nec purus porta pharetra. Nullam tincidunt turpis sed rutrum rutrum. Sed efficitur porta tortor sit amet pretium. Duis posuere lorem leo, vel elementum ante maximus congue. Integer porta augue at risus volutpat imperdiet.\n" +
                "\n" +
                "Etiam lobortis, dolor vel tempus hendrerit, risus sapien ultrices turpis, sit amet posuere odio enim sit amet diam. Mauris id accumsan urna. Aliquam quam lectus, lacinia iaculis pulvinar a, porttitor vel erat. Nam eu maximus purus. Fusce aliquet, mauris ac efficitur molestie, quam ligula rhoncus felis, et lacinia leo leo nec velit. Praesent eu diam nec libero finibus lobortis quis non odio. Vestibulum tempor turpis tellus, vitae fringilla libero iaculis bibendum. Nulla sed mattis turpis. Nullam dignissim et sem sed rhoncus. Nulla id sollicitudin tortor. In odio enim, egestas vitae lectus et, lobortis aliquet lorem. Maecenas dignissim, nibh id tempor tincidunt, augue elit dapibus dui, suscipit luctus mauris neque eu est. Pellentesque sed nisi maximus, pretium massa ultrices, aliquam nunc. Aliquam erat volutpat. Suspendisse mattis nibh est, sed efficitur ex ornare eget. Vestibulum nisi magna, pretium at tempor et, finibus vel diam.\n" +
                "\n" +
                "Proin et velit ex. Pellentesque tempus posuere mauris sed suscipit. Integer vitae bibendum ligula. Quisque non felis accumsan, tincidunt arcu vitae, commodo libero. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eu mauris et nisi posuere aliquam at vitae nulla. Sed id nisl leo. Proin elementum ex vitae odio suscipit ultricies. Integer vel dui lacus. Fusce et viverra eros. Sed augue sem, sollicitudin nec dictum quis, convallis et dolor. Ut lacus massa, dignissim ullamcorper ullamcorper non, mattis ac dolor.",
        ratings = 5f,
        servings = 4,
        totalTimeInMinute = 15,
        imageUrl = "https://images.unsplash.com/photo-1680199994489-22b5f4ccc620?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw1fHx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=500&q=60",
        ingredients = listOf(
            IngredientDetail(
                unit = "piece",
                amount = 1,
                note = "hm",
                ingredientId = "1",
            ),
            IngredientDetail(
                unit = "piece",
                amount = 1,
                note = "hm",
                ingredientId = "2",
            ),
            IngredientDetail(
                unit = "piece",
                amount = 1,
                note = "hm",
                ingredientId = "3",
            ),
            IngredientDetail(
                unit = "piece",
                amount = 1,
                note = "hm",
                ingredientId = "4",
            ),
            IngredientDetail(
                unit = "piece",
                amount = 1,
                note = "hm",
                ingredientId = "5",
            ),
            IngredientDetail(
                unit = "piece",
                amount = 1,
                note = "hm",
                ingredientId = "6",
            ),
        ),
        steps = listOf(
            "Put a large saucepan of water on to boil.",
            "Finely chop the 100g pancetta, having first removed any rind. Finely grate 50g pecorino cheese and 50g parmesan and mix them together.",
            "Beat the 3 large eggs in a medium bowl and season with a little freshly grated black pepper. Set everything aside.",
            "While the spaghetti is cooking, fry the pancetta with the garlic. Drop 50g unsalted butter into a large frying pan or wok and, as soon as the butter has melted, tip in the pancetta and garlic.",
            "Add extra pasta cooking water to keep it saucy (several tablespoons should do it). You don’t want it wet, just moist. Season with a little salt, if needed.",
            "Mix most of the cheese in with the eggs, keeping a small handful back for sprinkling over later.",
            "Keep the heat under the pancetta on low. When the pasta is ready, lift it from the water with a pasta fork or tongs and put it in the frying pan with the pancetta. Don’t worry if a little water drops in the pan as well (you want this to happen) and don’t throw the pasta water away yet.",
        ),

        ),
)
