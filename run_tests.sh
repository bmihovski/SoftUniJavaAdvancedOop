#!/bin/bash
# Navigate to the project directory
cd "$(dirname "$0")" || exit

# Build the project using Maven
mvn clean package

# Check if the build was successful
if [ $? -ne 0 ]; then
  echo "Maven build failed!"
  exit 1
fi
awk '/===/{n++;next}{print >"tests/Test.in.split."n}' tests/Test.in.txt
awk '/===/{n++;next}{print >"tests/Test.out.split."n}' tests/Test.out.txt
INPUTS=(tests/Test.in.split.*)
OUTPUTS=(tests/Test.out.split.*)
for i in "${!INPUTS[@]}"; do
  echo "Running test $((i + 1))..."
  java -jar target/CliTest-1.0-SNAPSHOT.jar <"${INPUTS[i]}" >tests/temp.out.txt
  if diff -Z -B -q tests/temp.out.txt "${OUTPUTS[i]}" >/dev/null; then
    echo "Test $((i + 1)) passed!"
  else
    echo "Test $((i + 1)) failed!"
    echo "Input was:"
    cat "${INPUTS[i]}"
    echo "Output diff:"
    diff -Z -B tests/temp.out.txt "${OUTPUTS[i]}"
  fi
done
rm tests/temp.out.txt tests/Test.in.split.* tests/Test.out.split.*
