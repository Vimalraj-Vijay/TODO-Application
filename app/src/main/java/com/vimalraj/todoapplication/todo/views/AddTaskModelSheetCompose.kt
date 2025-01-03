package com.vimalraj.todoapplication.todo.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vimalraj.todoapplication.R
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewModel
import com.vimalraj.todoapplication.todo.viewmodel.TodoViewState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskModalSheet(
    todoViewState: TodoViewState?,
    todoViewModel: TodoViewModel,
    onDismissed: () -> Unit,
) {
    if (todoViewState != null) {
        val isUpdateTask by remember { mutableStateOf(todoViewState.isUpdateTask) }
        val index by remember { mutableIntStateOf(todoViewState.selectedIndex) }

        val sheetState = rememberModalBottomSheetState()
        var expanded by remember { mutableStateOf(false) }
        var error by remember { mutableStateOf(false) }

        var mSelectedText by remember {
            val value = if (isUpdateTask) {
                todoViewState.todoList[index].priority.name
            } else {
                todoViewState.dropDownList[0]
            }
            mutableStateOf(value)

        }
        var text by remember {
            val value = if (isUpdateTask) {
                todoViewState.todoList[index].task
            } else {
                ""
            }
            mutableStateOf(value)
        }
        ModalBottomSheet(
            onDismissRequest = {
                onDismissed.invoke()
            }, sheetState = sheetState
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                    value = text,
                    onValueChange = {
                        text = it
                        if (error && text.isNotEmpty()) {
                            error = false
                        }
                    },
                    label = { Text(stringResource(R.string.enter_task)) },
                    isError = error,
                    supportingText = {
                        if (error) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(R.string.field_should_not_be_empty),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    })
                Box(modifier = Modifier.fillMaxWidth()) {
                    ExposedDropdownMenuBox(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .padding(horizontal = 8.dp),
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }) {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable),
                            value = mSelectedText,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text(text = stringResource(R.string.task_priority)) },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                        )

                        ExposedDropdownMenu(expanded = expanded, onDismissRequest = {
                            expanded = false
                        }) {
                            todoViewState.dropDownList.forEachIndexed { index, text ->
                                DropdownMenuItem(
                                    text = { Text(text = text) },
                                    onClick = {
                                        mSelectedText =
                                            todoViewState.dropDownList[index]
                                        expanded = false
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                )
                            }
                        }


                    }
                }
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp)
                    .padding(top = 8.dp), onClick = {
                    if (text.isEmpty()) {
                        error = true
                    } else {
                        if (isUpdateTask) {
                            todoViewModel.insertOrUpdateTask(
                                index = index,
                                task = text,
                                priority = mSelectedText
                            )
                        } else {
                            todoViewModel.insertOrUpdateTask(
                                task = text,
                                priority = mSelectedText
                            )
                        }
                        onDismissed.invoke()
                    }
                }) {
                    Text(
                        modifier = Modifier.padding(all = 4.dp),
                        text = if (isUpdateTask) {
                            stringResource(R.string.update_task)
                        } else {
                            stringResource(R.string.save_task)
                        }
                    )
                }
                if (isUpdateTask) {
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = colorResource(R.color.white),
                            containerColor = colorResource(R.color.red30)
                        ),
                        onClick = {
                            todoViewModel.deleteTask(todoViewState.todoList[index].id)
                            onDismissed.invoke()
                        }) {
                        Text(
                            modifier = Modifier.padding(all = 4.dp),
                            text = stringResource(R.string.delete_task)
                        )
                    }
                }
            }
        }

    }
}