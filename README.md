
# CIFTools-java (SwingJS)
A GitHub fork from ([rcsb CIFTools](https://github.com/rcsb/ciftools-java)),
CIFTools-java (SwingJS) implements reading, building, and writing of mmCIF files, both ([standard text format](http://www.iucr.org/resources/cif/spec/version1.1/cifsyntax)) as well as their ([BinaryCIF](https://github.com/dsehnal/BinaryCIF)) eqivalent. This lightweight and efficient 100 KB package of around 80 classes works as a stand-alone zero-dependency Java library for any Java application as well as a component for any ([SwingJS](https://chemapps.stolaf.edu/swingjs/site/swingjs/examples/)) Java+JavaScript browser-based app that is derived from Java using the ([java2script](https://github.com/BobHanson/java2script)) Eclipse plug-in. 

The SwingJS version of CIFTools utilizes a set of customizable property files for CIF tag definition, leading to a minimum set of methods for implementation. Reading of binaryCIF files utilizes the ([MessagePack](https://msgpack.org/index.html)] binary data transfer protocol.  "Categories" and "columns" are created dynamically using standard CIF tag notation such as "atom_site" and "cartn_x", respectively. 

Note that this SwingJS fork of rcsb/CIFTools-java is a slimmed-down version of the API-driven ([RCSB implementation](https://github.com/rcsb/ciftools-java)). It requires only four data retrieval methods: `.getCategory(String)`, `.getIntColumn(String)`, `.getFloatColumn(String)`, and `.getStrColumn(String))`, rather than the more mmCIF-specialized .getAtomSite().getCartnX() sort of methods implemented in CIFTools-Java. Other than that, it is a fully functional implementation of CIFTools-java that is optimized for speed in both Java and JavaScript. Approximate parsing time for binary 4v4g (83 MB text; 9 MB binary) is 100-200 ms (4-yr old dual-core I4 HP ThinkPad). _Oddly enough, this time range depends upon the server, with EBI binaryCIF taking much longer to deliver but parsing in only 100 ms, while RCSB binaryCIF is considerably faster in delivery but takes 200 ms to parse, for reasons that are unclear to me._

The package can be used by any Java or JavaScript application, though the general expectation is that it will be used with SwingJS applications that are produced specifically for functioning both in Java and in JavaScript, where the programming is in Java, and the derived JavaScript is created dynamically along with the Java class files using the java2script Eclipse plug-in. 

For example, 

```Java
   include org.rcsb.cif.generic.CifFile;
   include org.rcsb.cif.generic.CifIO;
   
   CifFile cifFile = CifIO.readFromURL(new URL("https://models.rcsb.org/3j9m.bcif"));
   double[][] coords = cifFile.getFirstBlock()
                              .getCategory("atom_site")
                              .fillFloat("cartn_x","cartn_y","cartn_z");  
```

CIF category and column are case-insensitive.   

## Getting Started

The SwingJS CIFTools package can be downloaded from GitHub directly. The JavaScript-ready package for standard SwingJS is in the dist/ directory as a zip file. That distribution is a fully functional SwingJS application including the full java2script/SwingJS J2S-JVM and J2S-JRE. For addition to any SwingJS application, only the site/swingjs/j2s/org/rcsb/cif directory subset is necessary.

Note that the base branch for this project is swingjs, not master. (I know, that's odd. The idea was to keep the master as just minor changes and suggestions for pushing to rcsb, reserving the swingjs branch for more major changes that would not be pushed to rcsb/ciftools.)

## TODO

This is a minimal implementation that could be elaborated more fully to provide additional ease-of-use methods. That fact that it was produced in two weeks start to finish definitely shows. Appologies fo that. The Maven POM file is present, but the code has not actually been released yet as a Maven project. It would be great is someone would get that cleaned up.

