package com.mobileassistant.smartvisionbuildnblog.ui.about_features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.mobileassistant.smartvisionbuildnblog.R

class AboutFeaturesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            // Dispose the Composition when viewLifecycleOwner is destroyed
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
            )
            val featureDescriptionList =
                resources.getStringArray(R.array.feature_description).toList()

            setContent {
                AboutFeatureContent(featureDescriptionList)
            }
        }
    }

    @Composable
    fun AboutFeatureContent(featureDescriptionList: List<String>) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = getString(R.string.about_us_content),
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 20.sp)
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(featureDescriptionList) { featureDescription ->
                    Text(
                        text = featureDescription,
                        style = TextStyle(fontSize = 20.sp),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}