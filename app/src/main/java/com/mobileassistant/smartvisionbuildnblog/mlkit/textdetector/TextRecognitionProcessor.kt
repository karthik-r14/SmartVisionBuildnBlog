/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mobileassistant.smartvisionbuildnblog.mlkit.textdetector

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface
import com.mobileassistant.smartvisionbuildnblog.mlkit.utils.GraphicOverlay
import com.mobileassistant.smartvisionbuildnblog.mlkit.utils.VisionProcessorBase
import com.mobileassistant.smartvisionbuildnblog.ui.SendValue

const val ACTIVATED_STATUS_TEXT = "Announcement Activated"
const val DEACTIVATED_STATUS_TEXT = "Announcement Deactivated"

/** Processor for the text detector demo. */
class TextRecognitionProcessor(
    private val context: Context,
    textRecognizerOptions: TextRecognizerOptionsInterface,
    private val textToSpeech: TextToSpeech? = null,
    private val isAnnouncementEnabledOnScreenLoad: Boolean? = false
) : VisionProcessorBase<Text>(context), SendValue {
    private val textRecognizer: TextRecognizer = TextRecognition.getClient(textRecognizerOptions)
    private val shouldGroupRecognizedTextInBlocks: Boolean = true
    private val showLanguageTag: Boolean = false
    private val showConfidence: Boolean = true
    private var isAnnouncementEnabled: Boolean = isAnnouncementEnabledOnScreenLoad == true

    override fun stop() {
        super.stop()
        textRecognizer.close()
    }

    override fun detectInImage(image: InputImage): Task<Text> {
        return textRecognizer.process(image)
    }

    override fun onSuccess(text: Text, graphicOverlay: GraphicOverlay) {
        announceTextToUserIfEnabled(text.text)
        logExtrasForTesting(text)
        graphicOverlay.add(
            TextGraphic(
                graphicOverlay,
                text,
                shouldGroupRecognizedTextInBlocks,
                showLanguageTag,
                showConfidence
            )
        )
    }

    override fun onFailure(e: Exception) {
        Log.w(TAG, "Text detection failed.$e")
    }

    private fun announceTextToUserIfEnabled(textContent: String) {
        if (isAnnouncementEnabled) {
            textToSpeech?.speak(textContent, TextToSpeech.QUEUE_ADD, null, "")
        }
    }

    companion object {
        private const val TAG = "TextRecProcessor"
        private fun logExtrasForTesting(text: Text?) {
            if (text != null) {
                Log.v(MANUAL_TESTING_LOG, "Detected text has : " + text.textBlocks.size + " blocks")
                for (i in text.textBlocks.indices) {
                    val lines = text.textBlocks[i].lines
                    Log.v(
                        MANUAL_TESTING_LOG,
                        String.format("Detected text block %d has %d lines", i, lines.size)
                    )
                    for (j in lines.indices) {
                        val elements = lines[j].elements
                        Log.v(
                            MANUAL_TESTING_LOG,
                            String.format("Detected text line %d has %d elements", j, elements.size)
                        )
                        for (k in elements.indices) {
                            val element = elements[k]
                            Log.v(
                                MANUAL_TESTING_LOG,
                                String.format("Detected text element %d says: %s", k, element.text)
                            )
                            Log.v(
                                MANUAL_TESTING_LOG, String.format(
                                    "Detected text element %d has a bounding box: %s",
                                    k,
                                    element.boundingBox!!.flattenToString()
                                )
                            )
                            Log.v(
                                MANUAL_TESTING_LOG, String.format(
                                    "Expected corner point size is 4, get %d",
                                    element.cornerPoints!!.size
                                )
                            )
                            for (point in element.cornerPoints!!) {
                                Log.v(
                                    MANUAL_TESTING_LOG, String.format(
                                        "Corner point for element %d is located at: x - %d, y = %d",
                                        k,
                                        point.x,
                                        point.y
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun setAnnouncementStatus(isEnabled: Boolean) {
        val statusText = if (isEnabled) {
            ACTIVATED_STATUS_TEXT
        } else {
            DEACTIVATED_STATUS_TEXT
        }
        textToSpeech?.speak(statusText, TextToSpeech.QUEUE_FLUSH, null, "")
        isAnnouncementEnabled = isEnabled
    }
}
