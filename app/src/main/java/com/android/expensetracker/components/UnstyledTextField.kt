package com.android.expensetracker.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.android.expensetracker.ui.theme.Primary
import com.android.expensetracker.ui.theme.TextPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnstyledTextField(
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  readOnly: Boolean = false,
  textStyle: TextStyle = LocalTextStyle.current,
  label: @Composable (() -> Unit)? = null,
  placeholder: @Composable (() -> Unit)? = null,
  arrangement: Arrangement.Horizontal = Arrangement.Start,
  leadingIcon: @Composable (() -> Unit)? = null,
  trailingIcon: @Composable (() -> Unit)? = null,
  supportingText: @Composable (() -> Unit)? = null,
  isError: Boolean = false,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions.Default,
  singleLine: Boolean = false,
  maxLines: Int = Int.MAX_VALUE,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  shape: Shape = TextFieldDefaults.filledShape,

) {
  // If color is not provided via the text style, use content color as a default
  val textColor = TextPrimary
  val mergedTextStyle =
    textStyle.merge(TextStyle(color = textColor))

  BasicTextField(value = value,
    onValueChange = onValueChange,
    enabled = enabled,
    readOnly = readOnly,
    textStyle = mergedTextStyle,
    cursorBrush = SolidColor(Primary),
    visualTransformation = visualTransformation,
    keyboardOptions = keyboardOptions,
    keyboardActions = keyboardActions,
    interactionSource = interactionSource,
    singleLine = singleLine,
    maxLines = maxLines,
    decorationBox = @Composable { innerTextField ->
      TextFieldDefaults.DecorationBox(
        value = value,
        visualTransformation = visualTransformation,
        innerTextField = innerTextField,
        placeholder = {
          Row(
            modifier = Modifier
              .fillMaxWidth(),
            horizontalArrangement = arrangement,
            verticalAlignment = CenterVertically
          ) {
            placeholder?.invoke()
          }
        },
        label = label,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        supportingText = supportingText,
        shape = shape,
        singleLine = singleLine,
        enabled = enabled,
        isError = isError,
        interactionSource = interactionSource,
        contentPadding = PaddingValues(horizontal = 20.dp),
        colors = TextFieldDefaults.colors (
          Color.Transparent,
          cursorColor = Primary,
          focusedIndicatorColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent,
          disabledIndicatorColor = Color.LightGray,
        ),
      )
    })
}