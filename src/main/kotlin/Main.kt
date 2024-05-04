import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


@Composable
fun App() {
    MaterialTheme {
        PasswordManagerScreen()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PasswordManagerScreen() {
    val masterPassword = remember { mutableStateOf("") }
    val selectedService = remember { mutableStateOf<String?>(null) }
    val servicePassword = remember { mutableStateOf("") }

    val services = listOf(
        "YouTube.com",
        "Google.com",
        "Twitter.com",
        "YouTube.com",
        "Google.com",
        "Twitter.com",
        "YouTube.com",
        "Google.com",
        "Twitter.com"
    )

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = masterPassword.value,
            onValueChange = { masterPassword.value = it },
            label = { Text("Master Password") },
            modifier = Modifier.fillMaxWidth()
        )

        if (masterPassword.value.isNotEmpty()) {
            Text("Select service:", modifier = Modifier.padding(top = 20.dp))
            LazyColumn(modifier = Modifier.padding(vertical = 1.dp).fillMaxWidth().height(200.dp)) {
                items(services) { service ->
                    Card(
                        onClick = { selectedService.value = service },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(service, modifier = Modifier.padding(all = 10.dp))
                    }
                }
            }
        }

        if (selectedService.value != null) {
            Text("Selected: ${selectedService.value}", modifier = Modifier.padding(vertical = 8.dp))
            OutlinedTextField(
                value = servicePassword.value,
                onValueChange = { servicePassword.value = it },
                label = { Text("Password for ${selectedService.value}") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
