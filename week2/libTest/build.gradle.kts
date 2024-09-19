plugins {
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8 //Both versions were originally 1_7, had to switch it to 1_8 to remove warnings when running main function in main file
    targetCompatibility = JavaVersion.VERSION_1_8
}