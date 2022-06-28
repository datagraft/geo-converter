
# Geo converter

This is a simple utility library to transform Geo files into a compatible CSV format.

This library current has support to extract .shp files into .csv files.

## Usage

Currently, library has two processing classes

* [GeoShape](https://github.com/datagraft/geo-converter/blob/master/src/main/java/net/datagraft/shape/GeoShape.java) : To convert .shp files

	``
	GeoShape shape = new GeoShape("absolute path of .shp");
    // writes a csv file		
	String csvFilePath =shape.writeCSV(destination path);
	``

* [GeoShapeFromZip](https://github.com/datagraft/geo-converter/blob/master/src/main/java/net/datagraft/shape/GeoShapeFromZip.java) : To convert .zip files that should contain .shp and .shx under same location

	``
	GeoShapeFromZip shape = new GeoShapeFromZip("absolute path of .zip");
    // writes a csv file		
	String csvFilePath =shape.writeCSV();
	``
## Requirements

* Java 1.8.x
* Maven 3.x

This is currently under **development**. 
Current version is released with minimal functional requirements

# 🐳 Build

You can build the library using Docker:

```bash
$ docker build . -t geo-converter
$ docker run -it --rm -v $(pwd):/mnt geo-converter cp /usr/src/app/target/geo-converter-0.0.1.jar /mnt
$ docker image rm geo-converter
```

Or Podman:

```bash
$ podman build . -t geo-converter
$ podman run -it --rm -v $(pwd):/mnt geo-converter cp /usr/src/app/target/geo-converter-0.0.1.jar /mnt
$ podman image rm geo-converter
```

## Questions or issues?

For posting information about bugs, questions and discussions please use the [Github Issues](https://github.com/datagraft/geo-converter/issues) feature.

## Core Team

- [Nikolay Nikolov](https://github.com/nvnikolov)
- [Nivethika Mahasivam](https://github.com/nivemaham)

## License
Available under the [Eclipse Public License](/LICENSE) (v1.0).
