package com.example.currencyconverter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currencyconverter.ui.theme.*


class CurrencyViewModel : ViewModel() {
    private var _currencies = MutableLiveData<List<Currency>>()
    val currencies: LiveData<List<Currency>>
        get() = _currencies

    init {
        _currencies.postValue(CURRENCIES)
    }

    fun search(query: String) {
        val matchedCurrencies: List<Currency>
        println("query: $query")
        matchedCurrencies = if (query.isNotEmpty()) {
            CURRENCIES.filter {
                it.name.contains(query, ignoreCase = true)
            }
        } else {
            CURRENCIES
        }
        _currencies.postValue(matchedCurrencies)
    }

}


@Composable
fun CurrencyScreen(
    searchViewModel: CurrencyViewModel = viewModel(),
    title: String,
    onClick: (String) -> Unit
) {
    Surface(color = ScreenBackgroundColor) {
        Scaffold(
            topBar = { SearchAppBar(searchViewModel) }
        ) { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxHeight()
                    .background(ScreenBackgroundColor)
            ) {
                CurrencyList(searchViewModel, title, onClick)
            }
        }
    }
}

@Composable
fun CurrencyList(
    viewModel: CurrencyViewModel,
    title: String,
    onClick: (String) -> Unit
) {
    val currencies = viewModel.currencies.observeAsState().value

    Column {
        Text(
            text = title,
            color = CurrencyColor,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp)
        )
        LazyColumn(modifier = Modifier.padding(vertical = 18.dp)) {
            if (!currencies.isNullOrEmpty()) {
                items(currencies) { currency ->
                    CurrencyRow(currency, onClick)
                }
            }
        }
    }

}

@Composable
fun CurrencyRow(
    currency: Currency,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 10.dp
            )
            .clickable(onClick = { onClick(currency.name) }),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = currency.icon),
            contentDescription = "Currency Icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape),
        )
        Text(
            text = currency.name,
            style = MaterialTheme.typography.h6,
            color = CurrencyColor,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .wrapContentWidth(Alignment.Start)
        )
        Spacer(
            Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.Yellow)
        )
        Icon(
            Icons.Rounded.KeyboardArrowRight,
            contentDescription = "forward icon",
            tint = TextColor
        )
    }
    Divider(startIndent = 16.dp)
}

@Composable
private fun SearchAppBar(viewModel: CurrencyViewModel) {
    var query: String by rememberSaveable { mutableStateOf("") }
    var showClearIcon by rememberSaveable { mutableStateOf(false) }

    showClearIcon = query.isNotEmpty()

    TextField(
        value = query,
        onValueChange = { newQuery ->
            query = newQuery
            viewModel.search(newQuery)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                tint = TextColor,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            if (showClearIcon) {
                IconButton(onClick = { query = "" }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        tint = TextColor,
                        contentDescription = "Clear Icon"
                    )
                }
            }
        },
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        placeholder = {
            Text(
                text = stringResource(R.string.hint_search_query),
                color = TextColor

            )
        },
        textStyle = TextStyle.Default.copy(
            color = TextColor,
            background = SectionBackgroundColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = SectionBackgroundColor, shape = RectangleShape)
    )
}
