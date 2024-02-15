# Learn-Android-ViewModel-by-UseCases
This repository contains multiple android projects. Each Android project represents a special usecase of Android ViewModel. 
We could just start learning by describing what **ViewModel** is but the purpose of this repository is to observe different use cases of **Android ViewModel**.

## **[SampleProject1](https://github.com/gobinda1547/Learn-Android-ViewModel-by-Use-Cases/tree/master/SampleProject1)**
This project proves that by default, data realated to **Activity** or **Fragment**, doesn't survive configuration changes. Some common configuration changes mechanisms are - Screen rotation, Display size change, Locale change, etc. However, By using this project, you will only be able to test that data will not servive after a configuration change. but the solution that we sould apply to handle the configuration change - is not discussed here.
**[More details](https://medium.com/@gobinda.paul.1547/a-sample-project-to-proof-that-by-default-data-related-to-android-activity-doesnt-survive-c7d8235f1df4)**


## **[SampleProject2](https://github.com/gobinda1547/Learn-Android-ViewModel-by-Use-Cases/tree/master/SampleProject2)**
This project is a simple example of how we can restore data by using **SavedInstanceState/Bundle** when an **Activity** gets restored due to configuration changes. This is an old approch - before the **ViewModel** was introduced.
**[More details](https://medium.com/@gobinda.paul.1547/handling-activity-configuration-changes-by-using-savedinstancestate-or-bundle-d62c1c09ad87)**


## **[SampleProject3](https://github.com/gobinda1547/Learn-Android-ViewModel-by-Use-Cases/tree/master/SampleProject3)**
This project is a simple example of how we can restore data by using **ViewModel** when an **Activity** gets restored due to configuration changes. This project also shows how we should declare our **ViewModel** in **Activity** scope.
**[More details](https://medium.com/@gobinda.paul.1547/using-android-viewmodel-in-activity-scope-7a889096c563)**


## **[SampleProject4](https://github.com/gobinda1547/Learn-Android-ViewModel-by-Use-Cases/tree/master/SampleProject4)**
This project shows how we should declare our **ViewModel** in **Fragment** scope.
**[More details](https://medium.com/@gobinda.paul.1547/using-android-viewmodel-in-fragment-scope-eb509a74036e)**


## **[SampleProject5](https://github.com/gobinda1547/Learn-Android-ViewModel-by-Use-Cases/tree/master/SampleProject5)**
This project explains the concept of **Shared ViewModel**. Here, we will use **ViewModel** in our **Fragment** class but we will attach the **ViewModel** in **Activity** scope. Since we have added the **ViewModel** in **Activity** scope so, until the **Activity** gets destroyed the **ViewModel** will be there. And If there are multiple **Fragments** added in the **Activity**, then these **Fragments** will be able to pass data between them via the **ViewModel**. Since the **ViewModel** is shared among these **Fragments**, that's why it's known as **Shared ViewModel** concept.
**[More details](https://medium.com/@gobinda.paul.1547/shared-viewmodel-concept-in-android-c4cba209db6f)**


## **[SampleProject6](https://github.com/gobinda1547/Learn-Android-ViewModel-by-Use-Cases/tree/master/SampleProject6)**
This project demonstrate how to use **ViewModel** with **Hilt** dependency injection library and in **Activity** scope. This project does maintain the proper **MVVM** rules (The data source is separated by a **Repository** class & it will be injected in the **ViewModel** class by **Hilt** itself).
**[More details](https://medium.com/@gobinda.paul.1547/how-to-use-android-viewmodel-with-hilt-dependency-6c66e07cf5c4)**
