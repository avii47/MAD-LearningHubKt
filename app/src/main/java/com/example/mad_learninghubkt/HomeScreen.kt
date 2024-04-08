package com.example.mad_learninghubkt

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.mad_learninghubkt.data.CategoriesItem
import com.example.mad_learninghubkt.data.CoursesItem
import com.example.mad_learninghubkt.ui.theme.BlueEnd
import com.example.mad_learninghubkt.ui.theme.BlueStart
import com.example.mad_learninghubkt.ui.theme.GreenStart
import com.example.mad_learninghubkt.ui.theme.OrangeStart
import com.example.mad_learninghubkt.ui.theme.PurpleStart
import com.example.mad_learninghubkt.util.CourseDataStore

val courseDataList = CourseDataStore.getCourseData()

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview
@Composable
fun HomeScreen(navController: NavController) {
    var filterTitle by remember { mutableStateOf("Courses for you") }
    var filteredCourseDataList by remember { mutableStateOf(courseDataList) }

    Scaffold(
        bottomBar = {
            Spacer(modifier = Modifier.height(80.dp))
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            TopSection()
            CardSection()
            CategorySection() { categoryName ->
                filterTitle = "$categoryName Courses"
                filteredCourseDataList = courseDataList.filter { it.category == categoryName }
            }
            Spacer(modifier = Modifier.size(16.dp))
            CourseSection(filterTitle, filteredCourseDataList, navController)
        }
    }
}

@Composable
fun TopSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Column {
                Text(
                    text = "Welcome to",
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Learning Hub",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable {}
                    .padding(6.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clickable {}
                    .padding(6.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notifications",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}

fun getGradient(
    startColor: Color,
    endColor: Color,
): Brush {
    return Brush.horizontalGradient(
        colors = listOf(startColor, endColor)
    )
}

@Composable
fun CardSection() {
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(getGradient(BlueStart, BlueEnd))
                .fillMaxWidth()
                .height(160.dp)
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Short Courses",
                    color = Color.White,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )

                Image(
                    painter = painterResource(id = R.drawable.heading_img),
                    contentDescription = "Short Courses Card",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

val categoryList = listOf(
    CategoriesItem(
        icon = R.drawable.it_icon,
        name = "IT",
        background = OrangeStart
    ),

    CategoriesItem(
        icon = R.drawable.medical_icon,
        name = "Medical",
        background = BlueStart
    ),

    CategoriesItem(
        icon = R.drawable.laguages_icon,
        name = "Languages",
        background = PurpleStart
    ),

    CategoriesItem(
        icon = R.drawable.engineering_icon,
        name = "Engineering",
        background = GreenStart
    ),

    CategoriesItem(
        icon = R.drawable.economics_icon,
        name = "Economics",
        background = OrangeStart
    ),

    CategoriesItem(
        icon = R.drawable.technology_icon,
        name = "Technology",
        background = BlueStart
    ),
)

@Composable
fun CategorySection(onCategoryClicked: (String) -> Unit) {
    Column {
        Text(
            text = "Categories",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow {
            items(categoryList.size) { index ->
                CategoryItem(index) { categoryName ->
                    onCategoryClicked(categoryName)
                }
            }
        }
    }
}

@Composable
fun CategoryItem(
    index: Int,
    onCategoryClicked: (String) -> Unit
) {
    val category = categoryList[index]
    val iconPainter = painterResource(id = category.icon)
    var lastPaddingEnd = 0.dp
    if (index == categoryList.size - 1) {
        lastPaddingEnd = 16.dp
    }

    Box(modifier = Modifier.padding(start = 16.dp, end = lastPaddingEnd)) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .size(120.dp)
                .clickable {
                    onCategoryClicked(category.name)
                }
                .padding(13.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(category.background)
                    .padding(6.dp)
            ) {
                Image(
                    painter = iconPainter,
                    contentDescription = category.name,
                    modifier = Modifier.size(40.dp)
                )
            }

            Text(
                text = category.name,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp
            )

        }
    }
}

@Composable
fun CourseSection(
    filterTitle: String,
    filteredCourseDataList: List<CoursesItem>,
    navController: NavController
) {
    Column {
        Text(
            text = filterTitle,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        filteredCourseDataList.forEachIndexed { index, courseItem ->
            CourseItem(index, courseItem, navController)
        }
    }
}

@Composable
fun CourseItem(
    index: Int,
    course: CoursesItem,
    navController: NavController
) {
    val iconPainter = rememberImagePainter(course.image)

    Box(
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 16.dp)
            .clickable {
                navController.navigate(route = "${Navigation.CourseDetails.route}/${course.cid}")
            }
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .fillMaxWidth()
                .height(120.dp)
                .padding(vertical = 12.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = iconPainter,
                    contentDescription = course.title,
                    modifier = Modifier.width(60.dp)
                )

                Column {
                    Text(
                        text = course.title,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = course.level,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = course.duration.toString() + " hours",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
