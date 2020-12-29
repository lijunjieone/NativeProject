# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# Declares and names the project.

project("nativeproject")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             native-lib.cpp
             PngPreviewController.cpp
        ./librenderer/egl_core.cpp
        ./librenderer/pic_preview_controller.cpp
        ./librenderer/pic_preview_render.cpp
        ./librenderer/pic_preview_texture.cpp
        ./librenderer/rgba_frame.cpp
        ./3rdparty/libpng/png_decoder.cpp
        ./3rdparty/libpng/example.c
        ./3rdparty/libpng/image.c
        ./3rdparty/libpng/png.c
        ./3rdparty/libpng/pngerror.c
        ./3rdparty/libpng/pngget.c
        ./3rdparty/libpng/pngmem.c
        ./3rdparty/libpng/pngpread.c
        ./3rdparty/libpng/pngread.c
        ./3rdparty/libpng/pngrio.c
        ./3rdparty/libpng/pngrtran.c
        ./3rdparty/libpng/pngrutil.c
        ./3rdparty/libpng/pngset.c
        ./3rdparty/libpng/pngtrans.c
        ./3rdparty/libpng/pngwio.c
        ./3rdparty/libpng/pngwrite.c
        ./3rdparty/libpng/pngwtran.c
        ./3rdparty/libpng/pngwutil.c



        )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.
include_directories(./librenderer)
include_directories(./libcommon)
include_directories(./3rdparty/libpng)


find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       native-lib
        -landroid #native_window
        -lz
        -lGLESv2
        -lEGL
        # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )