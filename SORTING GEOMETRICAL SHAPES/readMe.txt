/****************************************************************************************************************
*Prgram: 	SORTING GEOMETRICAL SHAPES															
*Author: 	Madhu Madhavan
								
*Date: 		Feb 8th 2022																						
*****************************************************************************************************************
**/

Program description:  
--------------------
A program to sort a large number of unsorted shapes with respect to their height, base area or volume. 
This program uses 6 different sorting algorithms to sort these shapes. It also displays the time taken to run these sorts. 

The application has been tested using various parameters. 


How to run the application:
---------------------------
The file needs to be run by an IDE such as Eclipse. 
This program is executable by running the AppDriver java file that is located in the application folder under 'src'. 
You can right click the AppDriver and select -Run As/Run Configurations. 
In that dialog box click on the 'Arguments' tab next to 'Main' tab and enter your arguments as per the examples below 
Example : -fpolyfor1.txt -Tv -Sb, -ta -sQ -f"res\polyfor3".txt, -tH -F"C:\temp\polyfor5.txt" -sB

Before you do this please remember to check if you are on the correct assignment and select the correct folder where your unsorted text file is located to avoid problems.

File name F, the compare type T and the sort type S are provided as parameters via command line. All examples below are valid inputs:

java -jar sort.jar -fpolyfor1.txt -Tv -Sb
java -jar sort.jar -ta -sQ -f"res\polyfor3".txt
java -jar sort.jar -tH -F"C:\temp\polyfor5.txt" -sB

where v is volume, h is height, a is base area, b is bubble, s is selection, i is insertion, m is merge, q is quick, and z is our groups sort 'Gnome'.

In the res folder we have provided a small txt file of 27 shapes used for testing the program. It called "polyfor1small.txt". 

See below the output of the following command : -f"res/polyfor1small.txt" -Th -Sz
-----------------------------------------------------------------------------------------------------------------------------------------------------

SquarePrism          height:  1461.72    :base area:   47555643.52     :volume:      69513225473.89 ; 
PentagonalPrism      height:  3304.16    :base area:   13304.43        :volume:      43960020.32    ; 
PentagonalPrism      height:  7508.95    :base area:   35897.71        :volume:      269553973.94   ; 
OctagonalPrism       height:  7954.53    :base area:   4409769303.14   :volume:      35077637805099.66; 
Pyramid              height:  8012.24    :base area:   24299723.78     :volume:      64898381974.95 ; 
Cone                 height:  8666.58    :base area:   29658.85        :volume:      85680317.91    ; 
SquarePrism          height:  10365.86   :base area:   436408770.38    :volume:      4523751343705.40; 
Cylinder             height:  12595.15   :base area:   1328875.95      :volume:      67094163600625.33; 
TriangularPrism      height:  12686.18   :base area:   2100848.71      :volume:      26651744843.79 ; 
Pyramid              height:  13081.02   :base area:   149630044.02    :volume:      652438065635.36; 
Cylinder             height:  15259.05   :base area:   1139242687.05   :volume:      84423865206897936.00; 
Cone                 height:  17869.86   :base area:   4351162501.78   :volume:      25918221581356.58; 
OctagonalPrism       height:  18289.02   :base area:   6133225310.09   :volume:      112170692627111.53; 
SquarePrism          height:  18642.35   :base area:   805292778.94    :volume:      15012549032137.59; 
Cone                 height:  19468.87   :base area:   2779558633.58   :volume:      18038292270914.72; 
Cone                 height:  20305.60   :base area:   231819593.32    :volume:      1569079031044.93; 
SquarePrism          height:  20869.64   :base area:   1410703871.00   :volume:      29440880523635.93; 
TriangularPrism      height:  22074.92   :base area:   790496.68       :volume:      17450151077.16 ; 
OctagonalPrism       height:  24128.96   :base area:   6841519017.69   :volume:      165078704509459.38; 
TriangularPrism      height:  24471.51   :base area:   5848578.21      :volume:      143123522672.01; 
Pyramid              height:  24509.12   :base area:   1469173414.72   :volume:      12002713392111.63; 
TriangularPrism      height:  25559.62   :base area:   985063.00       :volume:      25177839774.19 ; 
Cone                 height:  28437.60   :base area:   931975332.92    :volume:      8834380886453.03; 
Cone                 height:  31456.95   :base area:   226339435.83    :volume:      2373316256228.56; 
Pyramid              height:  34651.31   :base area:   203944276.18    :volume:      2355645649500.20; 
Cone                 height:  37285.18   :base area:   7337907.88      :volume:      91198393102.84 ; 
Cylinder             height:  39349.55   :base area:   2567668950.88   :volume:      1265354946068134910.00; 

All arguments correct : arraysize-27 : Sort-My (Gnome) Sort : Comp:height Comparator
Time for Sorting with : 22 milliseconds
------------------------------------------------------------------------------------------------------------------------------------------------------

