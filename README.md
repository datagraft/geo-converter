
#About geo-converter
This is a simple utility library to transform Geo files into a compatible CSV format.

This library current has support to extract .shp files into .csv files.

#Usage
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
#Requirements

* Java 1.8.x
* Maven 3.x

This is currently under **development**. 
Current version is released with minimal functional requirements

