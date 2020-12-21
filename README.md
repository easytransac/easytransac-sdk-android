
# SDK Android EasyTransac

## Présentation

Le SDK d'EasyTransac permet de payer depuis votre application Android en toute sécurité en effectuant une demande de paiement à l'application EasyTransac directement, et ce, de manière automatisée. L'intégration est très simple à réaliser sur votre application et offre à vos clients une expérience de paiement optimale. Notre SDK Android vous **permet d'utiliser le Flash de carte, le NFC (sans contact) ainsi que le paiement multiple**, le tout très simplement.

![Direct payment](https://i.imgur.com/kg1pGHw.png) ![Multiple payment](https://i.imgur.com/OEI6GcI.png)

## Prérequis

- Application EasyTransac installée sur le périphérique Android (minimum Android version 4.4+).
- Version d'EasyTransac à jour (6.2, build 125 minimum et inférieur à 7.0.0)

Pour télécharger l'application et bénéficier du SDK, voici le lien vers le Google Play Store : https://play.google.com/store/apps/details?id=com.movidone.easytransac

## Importation du SDK

### Gradle
Editez le fichier build.gradle au niveau le plus haut de votre projet et ajoutez la dépendance à JitPack :

```java
allprojects {  
    repositories {  
        ...
        maven { url 'https://jitpack.io' }  
    }  
}
```

Importez la dépendance 
```css
	dependencies {
            implementation 'com.github.easytransac:easytransac-sdk-android:RELEASE_NUMBER'
	}
```
Lien vers JitPack : https://jitpack.io/#easytransac/easytransac-sdk-android

### Manuellement
Téléchargez et copiez dans votre projet le fichier de constantes : https://github.com/easytransac/easytransac-sdk-android/blob/master/easytransac_sdk/src/main/java/com/easytransac/easytransac_sdk/EasyTransacSDK.java

*Note : attention à toujours avoir la version du SDK à jour, c'est pourquoi l'utilisation de Gradle est conseillée.*

## Fonctionnement
Le SDK d'EasyTransac est intégré dans l'application elle-même, présente sur le Google Play Store. Pour l'utiliser, il faut appeler l'application via un Intent(). 

Pour illustrer le fonctionnement, deux dossiers sont présents dans le dépôt.

- **easytransac_sdk** : contient un seul et unique fichier référençant les paramètres à utiliser pour utiliser le SDK EasyTransac. Il s'agit de constantes, ce sont les paramètres à passer à votre Intent.
- **sample** : projet d'exemple montrant le fonctionnement et l'utilisation du SDK. Vous pouvez l'exécuter en remplaçant simplement les informations demandées.

Pour appeler l'application EasyTransac depuis votre application, il faut initialiser un nouvel Intent et procéder ainsi : 

```java
Intent intent = new Intent();  
// ces constantes sont présentes dans le SDK et correspondent au nom du package de l'application EasyTransac
intent.setClassName(EasyTransacSDK.EASYTRANSAC_PACKAGE_NAME, EasyTransacSDK.EASYTRANSAC_CLASS_NAME);  
// relatif à vos applications sur votre espace client EasyTransac
intent.putExtra(EasyTransacSDK.EXTRA_API_KEY, "YOUR_API_KEY==");
...
// int arbitraire pour onActivityResult
startActivityForResult(intent, RESULT_SDK);
```
## Paramètres de configuration de l'Intent

Les paramètres de configurations sont présentés et expliqués dans le fichier de constante du SDK, vous pouvez le consulter en cliquant sur le lien suivant : 

https://github.com/easytransac/easytransac-sdk-android/blob/master/easytransac_sdk/src/main/java/com/easytransac/easytransac_sdk/EasyTransacSDK.java

Ces paramètres se passent via un Intent, comme l'exemple du dessus avec la clé d'API (EXTRA_API_KEY).

```java
intent.putExtra(EasyTransacSDK.EXTRA_XXX, "value");
```

Voici un exemple complet pour un paiement d'un euro, sans 3DS, en NFC :

```java
Intent intent = new Intent();  
intent.setClassName(EasyTransacSDK.EASYTRANSAC_PACKAGE_NAME, EasyTransacSDK.EASYTRANSAC_CLASS_NAME);  
intent.putExtra(EasyTransacSDK.EXTRA_API_KEY, API_KEY);  
// montant en double
intent.putExtra(EasyTransacSDK.EXTRA_AMOUNT, 1.00);  
intent.putExtra(EasyTransacSDK.EXTRA_USE_3DS, false);
// FLASH, NFC, MANUAL
intent.putExtra(EasyTransacSDK.EXTRA_DETECTION_METHOD, "NFC");  
intent.putExtra(EasyTransacSDK.EXTRA_CUSTOMER_EMAIL, "sample@mail.com");   
startActivityForResult(intent, RESULT_SDK);
```

## Le retour d'une demande de paiement

Lorsque vous réalisez une demande de paiement, la main est passée à l'application EasyTransac qui va se charger de présenter vos informations à votre client et de le faire payer en toute sécurité. 

Lorsque le paiement est terminé (réussi, échoué, annulé ou autre erreur de configuration du SDK), un retour est fait dans votre application, via la méthode "onActivityResult()".

```java
@Override  
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {  
   super.onActivityResult(requestCode, resultCode, data);
   // le code passé préalablement dans le startActivityForResult()
   if (requestCode == RESULT_SDK) {
        ...
   }
 }
 ```

### Les codes de retours en cas d'erreur

```java
public static final int RESULT_CODE_PERMISSION_CAMERA_DENIED = -101;  
```
Vous souhaitez utiliser le FLASH pour encaisser mais la permission d'usage de la caméra a été refusée.

```java
public static final int RESULT_CODE_PERMISSION_NFC_NOT_ENABLED = -102;  
```
Vous souhaitez utiliser le NFC pour encaisser mais ce dernier n'a pas été activé sur le périphérique.

```java
public static final int RESULT_CODE_MISSING_PARAMETERS = -103;  
```
Un des paramètres obligatoires a été refusé par le SDK parce qu'il est manquant. Vérifiez que vous utilisez bien les bonnes constantes. 

```java
public static final int RESULT_CODE_WRONG_PARAMETERS = -104;  
```
Un des paramètres que vous avez renseigné est incorrect et ne correspond pas à ce qui est attendu.

```java
public static final int RESULT_CODE_UNEXPECTED_ERROR = -199;
```
Ce code apparaît lorsqu'un cas n'a pas été pris en compte dans le SDK. Vous ne devriez pas rencontrer ce code d'erreur. Si cette situation se présente nous sommes automatiquement notifiés du problème.

*Note : Vous pouvez avoir plus d'informations sur l'erreur courante en affichant la valeur de retour du SDK "RESULT_MESSAGE" retournée dans onActivityResult(), variable data.*

### Les codes de retours classiques (relatif à l'API Android)

```java
/** Standard activity result: operation canceled. */  
public static final int RESULT_CANCELED = 0;
```
Le client a demandé l'annulation de la demande de paiement, il convient donc d'annuler le "parcours de paiement" de votre côté également.
```java
/** Standard activity result: operation succeeded. */  
public static final int RESULT_OK = -1;
```
Tout s'est bien passé, vous trouverez les informations relatives au paiement dans "data".

### Traitement des résultats 

Le résultat d'une demande paiement contient plusieurs paramètres, en fonction du statut de l'opération.  

Le statut d'une opération peut être  : 
- **captured** : la transaction a réussi.
- **failed** : le paiement a échoué.
- **pending** : le paiement est toujours en attente (situation très rare, souvent relatif à un problème du partenaire bancaire). Vous serrez notifié de l'évolution du paiement soit par notification push. Vous pouvez également consulter la transaction via votre espace client ou via l'application EasyTransac.
- **issue** : une erreur s'est produite lors de la transmission du paiement.

```java
// rappel du montant de l'opération.
public final static String RESULT_AMOUNT = "RESULT_AMOUNT";  
```
```java
// identifiant EasyTransac de la transaction
public final static String RESULT_TID = "RESULT_TID";  
```
```java
// statut de la transaction
public static final String RESULT_STATUS = "RESULT_STATUS";  
```
```java
// message d'information relatif au paiement
// en cas d'échec, vous y trouverez la raison retournée par la banque
public static final String RESULT_MESSAGE = "RESULT_MESSAGE";  
```
```java
// date exacte du paiement
public static final String RESULT_DATE = "RESULT_DATE";
```

Pour récupérer les valeurs associées à ces clés, il vous suffit de récupérer la valeur dans l'objet "data", dans la fonction onActivityResult().

```java
@Override  
protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {  
   super.onActivityResult(requestCode, resultCode, data);
   // le code passé préalablement dans le startActivityForResult()
   if (requestCode == RESULT_SDK) {
        if (data != null) {
            Log.d("Example", data.getStringExtra(EasyTransacSDK.RESULT_STATUS));
        }
   }
 }
 ```

## Informations générales
 - Toutes les dates sont basées sur le fuseau Europe/Paris UTC/GMT +1.
 - L'encodage des caractères est fait selon l'ISO 8859-1

## Sample app
Le dossier sampleapp contient une application Android montrant l'intégration du SDK.
Lien vers l'activité principale : https://github.com/easytransac/easytransac-sdk-android/blob/master/sample/src/main/java/com/easytransac/sampleapp/MainActivity.java
