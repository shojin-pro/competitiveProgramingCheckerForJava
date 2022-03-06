#!/bin/bash
exec $(javac ../makeTest/MakeTestCase.java)
for i in $(seq 0 50); do
  S=$(printf "%04d\n" "${i}")
  exec $(java -classpath ../makeTest MakeTestCase > ../in/${S}.txt)
  echo "${S}.txt created."
done

exec $(javac ../makeTest/MakeCorrect.java)
for file in `\find ../in -maxdepth 1 -type f`; do
  VAR=$(basename ${file})
  exec $(java -classpath ../makeTest MakeCorrect < ../in/${VAR} > ../correct/${VAR})
  echo "${VAR} makeCorrect finished."
done

exec $(javac ../Main.java)
for file in `\find ../in -maxdepth 1 -type f`; do
  VAR=$(basename ${file})
  start_at=$(date +'%s.%3N')
  exec $(java -classpath ../ Main < ../in/${VAR} > ../out/${VAR})
  end_at=$(date +'%s.%3N')
  elapsed=$(echo "scale=3; ${end_at} - ${start_at}" | bc)
  echo "${VAR} test finised. time = ${elapsed}"
done

LOG=error$(date '+%Y%m%d%H%M%S').log;
exec $(touch "../log/${LOG}")
exec $(touch "../log/${LOG}_output")
exec $(javac ../verify/VerifyAnswer.java)
for file in `\find ../out -maxdepth 1 -type f`; do
  VAR=$(basename ${file})
  OUTFILE="../out/"
  CORRECTFILE="../correct/"
  INFILE="../in/"
  exec $(java -classpath ../verify/ VerifyAnswer ${VAR} ${OUTFILE} ${CORRECTFILE} ${INFILE} 2>> "../log/${LOG}" >>"../log/${LOG}_output")
  exec $(rm ${OUTFILE}${VAR})
  exec $(rm ${CORRECTFILE}${VAR})
  exec $(rm ${INFILE}${VAR})
  echo "${VAR} checking finised. file deleted."
done