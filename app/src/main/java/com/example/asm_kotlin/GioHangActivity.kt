package com.example.asm_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.asm_kotlin.ui.theme.ASM_KotlinTheme

data class CartProduct(
    val id: Int,
    val name: String,
    val price: Double,
    val imageResId: Int,
    var quantity: Int
)

class GioHangActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ASM_KotlinTheme {

            }
        }
    }
}

@Composable
fun CartScreen(navController: NavHostController) {
    val products = remember { mutableStateListOf<CartProduct>() }
    var totalAmount by remember { mutableStateOf(0.0) }

    // Initialize the products list only once
    if (products.isEmpty()) {
        products.add(CartProduct(1, "Minimal Stand", 25.0, R.drawable.img_1, 1))
        products.add(CartProduct(2, "Coffee Table", 150.0, R.drawable.img, 4))
        products.add(CartProduct(3, "Minimal Stand", 25.0, R.drawable.img_2, 2))
        products.add(CartProduct(4, "Minimal Desk", 150.0, R.drawable.img_3, 3))
        totalAmount = products.sumOf { it.price * it.quantity }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Box {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navController.navigate("${Screens.Bottom.route}")
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "My Cart",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        products.forEach { product ->
            CartItem(
                product = product,
                onQuantityChange = {
                    totalAmount = products.sumOf { it.price * it.quantity }
                },
                onRemoveProduct = {
                    products.remove(product)
                    totalAmount = products.sumOf { it.price * it.quantity }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total:",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = "$${String.format("%.2f", totalAmount)}",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.navigate("${Screens.Notification.route}")},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(50.dp)
            ) {
                Text(text = "Buy Now")
            }
        }
    }
}


@Composable
fun CartItem(
    product: CartProduct,
    onQuantityChange: () -> Unit,
    onRemoveProduct: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(8.dp))
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = product.imageResId),
            contentDescription = "Product Image",
            modifier = Modifier.size(80.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "$${String.format("%.2f", product.price)}",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        if (product.quantity > 1) {
                            product.quantity--; onQuantityChange()
                        }
                    },
                    modifier = Modifier.size(24.dp) // Adjust the size as needed
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.minus),
                        contentDescription = "Decrease Quantity"
                    )
                }

                Text(
                    text = "${product.quantity}",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                IconButton(
                    onClick = { product.quantity++; onQuantityChange() },
                    modifier = Modifier.size(24.dp) // Adjust the size as needed
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.more),
                        contentDescription = "Increase Quantity"
                    )
                }
            }
        }

        IconButton(
            onClick = { onRemoveProduct() },
            modifier = Modifier.size(24.dp) // Adjust the size as needed
        ) {
            Icon(
                painter = painterResource(id = R.drawable.remove),
                contentDescription = "Remove Product"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    ASM_KotlinTheme {

    }
}
