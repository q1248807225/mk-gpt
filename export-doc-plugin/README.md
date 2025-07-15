# Interface Exporter Plugin

This is a minimal IntelliJ IDEA plugin that exports interface documentation for selected methods, Java interfaces or API description files. The result can be written as HTML or Markdown.

## Building the Plugin

The build requires JDK 8 or JDK 11. Run:

```bash
./gradlew build
```

The generated plugin zip will be located in `build/distributions`.

## Using the Plugin

After installing, right‑click a method, interface, or a `.http`/`.api` file and choose **Export Interface Documentation**. You will be prompted to select **Markdown** or **HTML**. The generated file is saved in an `interface-docs` folder at the project root. The plugin targets Java 8 and therefore works on both Java 8 and Java 11 based IDEs.
