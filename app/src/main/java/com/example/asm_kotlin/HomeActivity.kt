package com.example.asm_kotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.asm_kotlin.Model.Type
import com.example.asm_kotlin.ui.theme.ASM_KotlinTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ASM_KotlinTheme {

            }
        }
    }
}

val typeList = listOf(
    Type(1, R.drawable.baseline_star_24, "Popular"),
    Type(2, R.drawable.outline_chair_alt_24, "Chair"),
    Type(3, R.drawable.outline_table_bar_24, "Table"),
    Type(4, R.drawable.outline_chair_24, "Armchair"),
    Type(5, R.drawable.outline_bed_24, "Bed")
)
@Composable
fun TypeItem(type: Type) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 19.dp)

    ) {
        Box(
            modifier = Modifier
                .padding(2.dp)
                .size(50.dp)
                .background(
                    color = Color("#F5F5F5".toColorInt()),
                    shape = RoundedCornerShape(9.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Image(painter = painterResource(id = type.icon), contentDescription = "Logo")
        }
        Text(text = type.tenTheLoai, textAlign = TextAlign.Center)
    }
}



data class Product(val imageResId: Int, val name: String, val price: Double)

@Composable
fun ProductGrid(navController: NavHostController) {
    val productList = mutableListOf<Product>()
    productList. add(Product(R.drawable.img, "Product 1", 100.0))
    productList. add(Product(R.drawable.img_1, "Product 2", 150.0))
    productList. add(Product(R.drawable.img_2, "Product 3", 200.0))
    productList. add(Product(R.drawable.img, "Product 4", 250.0))
    productList. add(Product(R.drawable.img, "Product 5", 100.0))
    productList. add(Product(R.drawable.img_1, "Product 6", 150.0))

    val scrollState = rememberScrollState()



    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
       /// cua cai type
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(16.dp)
        ) {
            typeList.forEach { type ->
                TypeItem(type = type)
            }
        }
        ///
        for (i in productList.indices step 2) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ProductItem(product = productList[i], modifier = Modifier.weight(1f),navController)
                if (i + 1 < productList.size) {
                    ProductItem(product = productList[i + 1], modifier = Modifier.weight(1f),navController)
                } else {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

        }

    }
}

@Composable
fun ProductItem(product: Product, modifier: Modifier = Modifier,navController: NavHostController) {

    Box(
        modifier = modifier
            .padding(8.dp).clickable {
                navController.navigate("${Screens.Detail.route}")
            }
            .clip(RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))

                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "$${product.price}",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Red),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_cart), // Replace with your actual cart icon
            contentDescription = "Add to Cart",
            tint = Color.Gray,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
                .size(24.dp)
        )
    }
}
