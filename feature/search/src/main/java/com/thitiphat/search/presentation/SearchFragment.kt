package com.thitiphat.search.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.thitiphat.data.forecast.constant.Constant
import com.thitiphat.search.R
import com.thitiphat.search.databinding.FragmentSearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val binding: FragmentSearchBinding by lazy {
        FragmentSearchBinding.inflate(
            layoutInflater
        )
    }

    private val viewModel: SearchViewModel by viewModel<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initObserver()
        binding.searchComposeView.apply {
            setContent {
                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
                SearchScreen()
            }
        }
        return binding.root
    }

    private fun initObserver() {
        viewModel.currentWeather.observe(viewLifecycleOwner) {
            viewModel.getForecast(
                viewModel.currentWeather.value?.coord?.lat.toString(),
                viewModel.currentWeather.value?.coord?.lon.toString()
            )
        }
        viewModel.forecast.observe(viewLifecycleOwner) {
            SearchFragmentDirections.goToAllDay(it, viewModel.currentWeather.value)
                .let { nav ->
                    findNavController().navigate(nav)
                }
        }
    }

    @Composable
    fun SearchScreen() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Black)
        ) {
            SearchBar()
            Remark()
            SearchResult()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SearchBar() {
        var text by remember {
            mutableStateOf(Constant.STRING_EMPTY)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            TextField(
                value = text,
                onValueChange = { typing ->
                    text = typing
                    viewModel.filterListOfItem(typing)
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.search__))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }

    @Composable
    fun SearchResult() {
        val data by remember {
            viewModel.filteredData
        }
        LazyColumn {
            items(data) {
                SearchResultItem(it)
            }
        }
    }

    @Composable
    fun SearchResultItem(data: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(8.dp))
                .height(72.dp)
                .clickable {
                    viewModel.getCurrentWeather(data)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp),
                text = data
            )
        }
    }

    @Composable
    fun Remark() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text(
                text = stringResource(id = R.string.remark),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }

    }


}