/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gobinda.navdrawercompose.ui.drawer

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gobinda.navdrawercompose.ui.navigation.AppScreenDetails
import com.gobinda.navdrawercompose.ui.theme.NavDrawerComposeTheme

@Composable
fun AppDrawerContent(
    drawerState: DrawerState,
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToSettings: () -> Unit,
    navigateToProfile: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(
        drawerState = drawerState,
        modifier = modifier,
    ) {
        Row(modifier = Modifier.padding(horizontal = 28.dp, vertical = 24.dp)) {
            Icon(Icons.Filled.Home, null)
            Spacer(modifier = Modifier.size(5.dp))
            Text("Test App")
        }
        NavigationDrawerItem(
            label = { Text(AppScreenDetails.HomeScreen.route) },
            icon = { Icon(Icons.Filled.Home, null) },
            selected = currentRoute == AppScreenDetails.HomeScreen.route,
            onClick = { navigateToHome(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text(AppScreenDetails.ProfileScreen.route) },
            icon = { Icon(Icons.Filled.AccountCircle, null) },
            selected = currentRoute == AppScreenDetails.ProfileScreen.route,
            onClick = { navigateToProfile(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text(AppScreenDetails.SettingsScreen.route) },
            icon = { Icon(Icons.Filled.Settings, null) },
            selected = currentRoute == AppScreenDetails.SettingsScreen.route,
            onClick = { navigateToSettings(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    NavDrawerComposeTheme() {
        AppDrawerContent(
            drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
            currentRoute = AppScreenDetails.HomeScreen.route,
            navigateToHome = {},
            navigateToProfile = {},
            navigateToSettings = {},
            closeDrawer = {}
        )
    }
}
