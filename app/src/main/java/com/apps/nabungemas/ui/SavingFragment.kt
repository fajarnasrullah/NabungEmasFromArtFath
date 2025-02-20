package com.apps.nabungemas.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apps.nabungemas.MainTopAppBar
import com.apps.nabungemas.R
import com.apps.nabungemas.data.SavingTable
import com.apps.nabungemas.ui.theme.MyApplicationTheme
import com.apps.nabungemas.utils.CurrencyAmount.currencyId
import com.apps.nabungemas.viewmodel.TransactionViewModel


@Composable
fun SavingScreen(
    navigateToAddSaving: () -> Unit,
    viewModel: TransactionViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val listSaving by viewModel.allSavingState.collectAsState(initial = listOf())
    Scaffold(
        topBar = {
            MainTopAppBar(
                title = "Saving",
                version = 1,
                navigateAdd = navigateToAddSaving
            )
        },
        backgroundColor = colorResource(id = R.color.grey_100)
    )
    { innerPadding ->
        SavingBody(
            itemList = listSaving,
            modifier = Modifier.padding(innerPadding),
            onDeletedItem = {
                viewModel.deleteSaving(it)
            }
        )
    }
}

@Composable
fun SavingBody(
    modifier: Modifier,
    itemList: List<SavingTable>?,
    onDeletedItem: (SavingTable) -> Unit
) {
    Column {
        if (itemList.isNullOrEmpty()) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No Data", style = MaterialTheme.typography.h6,)
            }
        } else {
            SavingList(modifier = modifier, itemList, onDeletedItem = onDeletedItem)
        }
    }


}

@Composable
fun SavingList(
    modifier: Modifier,
    itemList: List<SavingTable>,
    onDeletedItem: (SavingTable) -> Unit
) {
    LazyColumn {
        items(items = itemList) {
            SavingItem(modifier = modifier, it, onDeletedItem = onDeletedItem)
        }
    }
}

@Composable
fun SavingItem(
    modifier: Modifier,
    saving: SavingTable,
    onDeletedItem: (SavingTable) -> Unit
) {
    var deleteConfirm by rememberSaveable { mutableStateOf(false) }
    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 10.dp, end = 10.dp, bottom = 5.dp)
            .shadow(6.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(color = Color.White)

    ) {


        Column(modifier = modifier) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = Color(0xFFFFFDF5))
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier.weight(1f),
                        text = saving.savingCategory ?: "",
                        style = MaterialTheme.typography.h6,
                        color = Color.Black
                    )
                    IconButton(modifier = modifier
                        .size(24.dp)
                        .padding(end = 4.dp),
                        onClick = { deleteConfirm = true }) {
                        Icon(
                            modifier = modifier,
                            painter = painterResource(id = R.drawable.ic_delete),
                            tint = colorResource(id = R.color.grey_500),
                            contentDescription = ""
                        )
                    }
                    if (deleteConfirm) {
                        DeletedConfirmationAlert(modifier = modifier,
                            onCorfirm = {
                                deleteConfirm = false
                                if (it) {
                                    onDeletedItem(saving)
                                }
                            })
                    }
                }

            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = modifier.weight(1f)) {
                    Text(
                        modifier = modifier,
                        text = stringResource(
                            id = R.string.target,
                            currencyId(saving.target.toString())
                        ),
                        style = MaterialTheme.typography.body1,
                        color = Color.Black
                    )
                    Text(
                        modifier = modifier,
                        text = stringResource(
                            id = R.string.total_saving,
                            currencyId(saving.totalSaving.toString())
                        ),
                        style = MaterialTheme.typography.body1,
                        color = Color.Black
                    )
                }


                Text(
                    modifier = modifier.padding(start = 4.dp),
                    text = stringResource(
                        id = R.string.percentage,
                        saving.percentage ?: 0.0
                    ),
                    style = MaterialTheme.typography.h6,
                    color = Color.Black
                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun SavingItemPreview() {
    SavingItem(modifier = Modifier,
        saving = SavingTable(
            savingCategory = "Tabungan Menikah",
            target = 9000000,
            totalSaving = 900000,
            percentage = 9.0
        ),
        onDeletedItem = {})
}


@Preview(showBackground = true)
@Composable
fun SavingPreview() {
    MyApplicationTheme(darkTheme = false) {
        Scaffold(
            topBar = {
                MainTopAppBar(
                    title = "Saving",
                    version = 1,
                    navigateAdd = {})
            },
            backgroundColor = Color(0xFFF4F9FB)
        )
        { innerPadding ->
            SavingBody(
                itemList = listOf(
                    SavingTable(
                        savingCategory = "Tabungan Menikah",
                        target = 9000000,
                        totalSaving = 900000,
                        percentage = 9.0
                    ),
                    SavingTable(
                        savingCategory = "Tabungan Rumah",
                        target = 9000000,
                        totalSaving = 900000,
                        percentage = 11.0
                    )
                ),
                modifier = Modifier.padding(innerPadding),
                onDeletedItem = {}
            )
        }
    }
}