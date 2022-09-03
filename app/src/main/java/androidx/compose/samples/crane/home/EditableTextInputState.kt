package androidx.compose.samples.crane.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

class EditableTextInputState(
    initialText: String,
    private val hint: String
) {

    var text by mutableStateOf(initialText)

    val isHint: Boolean
        get() = text == hint

    companion object {
        val Saver: Saver<EditableTextInputState, *> = listSaver(
            save = {
                listOf(it.text, it.hint)
            },
            restore = {
                EditableTextInputState(
                    initialText = it[0],
                    hint = it[1]
                )
            }
        )
    }
}

@Composable
fun rememberEditableTextInpuState(hint: String): EditableTextInputState =
    rememberSaveable(hint, saver = EditableTextInputState.Saver) {
        EditableTextInputState(hint, hint)
    }