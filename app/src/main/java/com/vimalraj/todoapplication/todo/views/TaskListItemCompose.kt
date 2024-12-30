package com.vimalraj.todoapplication.todo.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.todo.database.entity.TodoModelAndEntity


@Composable
fun TaskListItem(todo: TodoModelAndEntity, isTaskCompletedClick: (isTicked: Boolean) -> Unit) {
    var isChecked = todo.isTaskCompleted

    Column(modifier = Modifier.padding(8.dp)) {
        Row {
            CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
                Checkbox(
                    modifier = Modifier.padding(all = 4.dp),
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                        isTaskCompletedClick.invoke(isChecked)
                    },
                )
            }
            Text(
                modifier = Modifier
                    .padding(all = 4.dp)
                    .align(Alignment.CenterVertically),
                text = stringResource(R.string.task, todo.task),
                textAlign = TextAlign.Start,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(
                    textDecoration = if (isChecked) {
                        TextDecoration.LineThrough
                    } else {
                        null
                    }
                )
            )
        }
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
            text = stringResource(R.string.last_modified_on, todo.createdAt),
            textAlign = TextAlign.Start
        )
    }
}