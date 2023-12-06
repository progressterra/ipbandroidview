# ipbandroidview
Библиотека компонентов для вашего приложения

# Содержание
Раздел дополняется

![Static Badge](https://img.shields.io/badge/License-MIT-blue)
# Кому пригодится ipbAndroidView?
Компаниям, создающим приложения в сфере Ecommerce
# Зачем вам ipbAndroidView?
- Сократить Time To Market вашего приложения
- Снизить косты на разработку
- Обеспечить приложение надёжными UI компонентами и [стабильным API](https://github.com/progressterra/ipbandroidapi)
# Примеры использования
## Ecommerce
Раздел дополняется
## ХАССП
Раздел дополняется
## Я тут!
Раздел дополняется
# Использования
## Добавление зависимости
Если используется Groovy
```groovy
dependencies {
    implementation "com.github.skydoves:landscapist-coil:$version"
}
```
Или же, если используется Kotlin Gradle DSL
```kotlin
dependencies {
    implementation("com.github.skydoves:landscapist-coil:$version")
}
```
## Настройка
### Кофигурационный файл `config.properties", находящийся в корневой папке проекта
```
# Индивидуальный ключ для доступа к платформе
accessKey=xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
# Ссылка на публичную оферту
offerUrl=https://iprobonus.com/
# Ссылка на политику конфедициальности
privacyUrl=https://iprobonus.com/
# Используемые в приложении цвета в hex формате. Если через запятую перечисляются два цвета, будет создан линейный горизонтальный градиент
primary=#35C290,#2E9399
secondary=#3E4555
secondary2=#CDCDD6
secondary3=#DCE8FF,#FFFFFF
tertiary=#B5B5BC
background=#F2F5FF
onBackground=#2E8E6C
onBackground2=#1A1A20
surface=#FFFFFF
surface2=#111111
onSurface=#FFFFFF
onSurface2=#101010
primaryPressed=#3D3D3D
primaryDisabled=#E4E4F0
secondaryPressed=#232427
error=#DF3636
success=#7ADB6B
info=#6980CF
warning=#DB742A
textPrimary=#111111
textPrimary2=#E82741
textPrimary3=#35C290,#2E9399
textSecondary=#6E7289
textTertiary=#9191A1
textTertiary2=#453896
textTertiary3=#28AB13
textTertiary4=#CA451C
textButton=#FFFFFF
textDisabled=#B5B5B5
textPressed=#24282E
iconPrimary=#111111
iconPrimary2=#E82741
iconPrimary3=#35C290,#2E9399
iconSecondary=#FFFFFF
iconSecondary2=#FFFFFF
iconTertiary=#B5B5BC
iconTertiary2=#4578DC,#453896
iconTertiary3=#B2FF75,#28AB13
iconTertiary4=#F6E651,#B80707
iconTertiary5=#1A1A20
iconPressed=#0F1215
iconDisabled=#B5B5B5
iconDisabled2=#EDF1FF
# Идентификаторы категорий, которые будут показаны на главном экране в соответствующем порядке
mainCategories=08db73df-fe78-4081-8ddf-e3646c33a2f7,08db73e0-6491-4112-8358-f524fbc64e72,08db73e0-70e5-49ab-80a7-680d3bd62600
# Хосты приложения, через запятую можно указать любое количество запасных для большей отказоустойчивости
productUrl=http://51.250.54.134:7023/,http://55.250.54.135:7023/
docsUrl=http://51.250.54.134:7107/
catalogUrl=http://51.250.54.134:7027/
cartUrl=http://51.250.54.134:7025/
balanceUrl=http://51.250.54.134:7009/
messengerUrl=http://51.250.54.134:7093/
paymentDataUrl=http://51.250.54.134:7095/
paymentUrl=http://51.250.54.134:7097/
scrmUrl=http://51.250.54.134:7021/
authUrl=http://51.250.54.134:7001/
mediaDataUrl=http://51.250.54.134:7089/
imhUrl=http://51.250.54.134:7701/
workWatchUrl=http://51.250.54.134:7103/
# Закругление кнопок в dp
buttonRounding=14
# Включена ли фукнция WorkWatch
workWatchEnabled=true
# Ключ для Яндекс Карты
yandexMapApiKey=2681c223-82e2-4e4d-a4fe-48a67f3fb97b
# Поля профиля, доступные и/или обязательный для ввода. Значения: name, soname, patronymic, sex, dateOfBirth, eMailGeneral
availableProfileFields=name,soname,dateOfBirth
mandatoryProfileFields=name,soname,dateOfBirth
```
### FCM
1. Подключите [Google Services Gradle Plugin](https://developers.google.com/android/guides/google-services-plugin?hl=ru)
2. Наследуйте базовый класс из библиотеки и укажите его в манифесте приложения
```kotlin
class AppCloudMessagingService : CloudMessagingService() {

    override val activityClass = AppActivity::class.java
    override val channelId = "IMH NC"
    override val notificationIconId = R.drawable.ic_notification
    override val notificationNameId = R.string.app_name
}
```
```xml
        <service
            android:name=".AppCloudMessagingService"
            android:exported="false">
            <intent-filter>
                <action android:name="com.google.firebase.MESSAGING_EVENT" />
            </intent-filter>
        </service>
```
### Подключите нужные компоненты к своии экранам или же воспользуйтесь готовыми
Раздел дополняется
