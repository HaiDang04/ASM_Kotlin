//package com.example.asm_kotlin
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.horizontalScroll
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.asm_kotlin.ui.theme.ASM_KotlinTheme
//
//class HomeActivity2 : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            GetCart(rememberNavController())
//        }
//    }
//}
//
//
//val listImg = listOf(
//    Product(R.drawable.img, "Product 1", 100.0),
//    Product(R.drawable.img_1, "Product 2", 150.0),
//    Product(R.drawable.img_2, "Product 3", 200.0),
//    Product(R.drawable.img_3, "Product 4", 250.0)
//)
//@Composable
//fun GetCart(navController: NavHostController){
//    ASM_KotlinTheme {
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(0.dp)
//        ) {
//            GetTitle()
//            HorizontalImageList(
//                imageList = listOf(
//                    Pair(R.drawable.star, "Popular"),
//                    Pair(R.drawable.chair, "Chair"),
//                    Pair(R.drawable.table, "Table"),
//                    Pair(R.drawable.sofa, "Armchair"),
//                    Pair(R.drawable.bed, "Bed")
//                )
//            )
////            Icon(
////                painter = painterResource(id = R.drawable.ic_launcher_foreground),
////                contentDescription = "Logo"
////            )
//
//            ProductGrid(productList = listImg)
//        }
//    }
//}
//
//data class Product(val imageResId: Int, val name: String, val price: Double)
//data class Product1(val imageResId: Int, val name: String)
//
//@Composable
//fun ProductGrid(productList: List<Product>) {
//    val scrollState = rememberScrollState()
//    Column(
//        modifier = Modifier
//            .verticalScroll(scrollState)
//            .padding(16.dp)
//    ) {
//        for (i in productList.indices step 2) {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                ProductItem(product = productList[i], modifier = Modifier.weight(1f))
//                if (i + 1 < productList.size) {
//                    ProductItem(product = productList[i + 1], modifier = Modifier.weight(1f))
//                } else {
//                    Spacer(modifier = Modifier.weight(1f))
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun ProductItem(product: Product, modifier: Modifier = Modifier) {
//    Box(
//        modifier = modifier
//            .padding(8.dp)
//            .clip(RoundedCornerShape(12.dp))
//    ) {
//        Column(
//            modifier = Modifier
//                .clip(RoundedCornerShape(12.dp))
//                .clickable { /* Handle click */ }
//                .padding(8.dp)
//        ) {
//            Image(
//                painter = painterResource(id = product.imageResId),
//                contentDescription = product.name,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(150.dp)
//                    .clip(RoundedCornerShape(12.dp))
//            )
//            Text(
//                text = product.name,
//                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
//                modifier = Modifier.padding(top = 8.dp)
//            )
//            Text(
//                text = "$${product.price}",
//                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Red),
//                modifier = Modifier.padding(top = 4.dp)
//            )
//        }
//        Icon(
//            painter = painterResource(id = R.drawable.shoping), // Replace with your actual cart icon
//            contentDescription = "Add to Cart",
//            tint = Color.Gray,
//            modifier = Modifier
//                .align(Alignment.BottomEnd)
//                .padding(8.dp)
//                .size(24.dp)
//        )
//    }
//}
//
//@Composable
//fun GetTitle() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(10.dp),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Row(
//            modifier = Modifier,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            GetImageSearch(Image = R.drawable.ic_search){
//
//            }
//            Spacer(modifier = Modifier.width(10.dp))
//            Column(
//                modifier = Modifier
//                    .align(Alignment.CenterVertically)
//                    .padding(10.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                GetTextTitle(title = "Make home")
//                GetTextTitle1(title = "BEAUTIFUL")
//            }
//        }
//        GetImageCart(Image = R.drawable.ic_cart){
//
//        }
//    }
//}
//
//@Composable
//fun HorizontalImageList(imageList: List<Pair<Int, String>>) {
//    val scrollState = rememberScrollState()
//    val selectedIndex = remember { mutableStateOf(-1) }
//    Row(
//        modifier = Modifier
//            .horizontalScroll(scrollState)
//            .padding(16.dp),
//        horizontalArrangement = Arrangement.spacedBy(35.dp)
//    ) {
//        imageList.forEachIndexed {index, (image, title) ->
//            Box(
//                modifier = Modifier
//                    .clickable { /* Handle click */ }
//            ) {
//                Column(
//                    modifier = Modifier.align(Alignment.Center),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Image(
//                        painter = painterResource(id = image),
//                        contentDescription = "Image Description",
//                        contentScale = ContentScale.Crop,
//                        modifier = Modifier
//                            .size(60.dp)
//                            .clip(RoundedCornerShape(12.dp))
//                            .background(color = Color.LightGray)
////                            .padding(10.dp)
//                            .clickable {
//                                selectedIndex.value = index
//                            }
//                            .background(
//                                color = if (selectedIndex.value == index) Color.Black else Color.LightGray,
//                                shape = RoundedCornerShape(12.dp)
//                            )
//                            .padding(10.dp)
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(
//                        text = title,
//                        style = MaterialTheme.typography.bodyMedium,
//                        color = Color.Gray
//                    )
//                }
//            }
//        }
//    }
//}
//
//
//
//
//@Composable
//fun GetImageSearch(Image: Int, onClick: () -> Unit) {
//    Image(
//        painter = painterResource(id = Image),
//        contentDescription = null,
//        modifier = Modifier
//            .padding(10.dp, 0.dp, 63.dp, 0.dp)
//            .size(25.dp)
//            .clickable { onClick() }
//
//    )
//}
//
//@Composable
//fun GetTextTitle(title: String) {
//    Text(
//        text = title,
//        modifier = Modifier,
//        fontSize = 20.sp,
//        color = Color.LightGray,
//        fontFamily = FontFamily.Serif,
//        fontWeight = FontWeight.W500
//
//    )
//}
//
//@Composable
//fun GetTextTitle1(title: String) {
//    Text(
//        text = title,
//        modifier = Modifier,
//        fontSize = 25.sp,
//        fontWeight = FontWeight.Bold,
//        color = Color.Black,
//        fontFamily = FontFamily.Serif
//    )
//}
//
//@Composable
//fun GetImageCart(Image: Int, onClick: () -> Unit) {
//    Image(
//        painter = painterResource(id = Image),
//        contentDescription = null,
//        modifier = Modifier
//            .size(45.dp)
//            .clickable { onClick() }
//            .padding(0.dp, 0.dp, 15.dp, 0.dp)
//    )
//}