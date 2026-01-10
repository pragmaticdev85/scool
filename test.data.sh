#/bin/sh
export API_BASE_URL='http://localhost:8080/api/'
export CONTACT_ADDRESSES_URI='contact-addresses'
export CURRICULA_URI='curricula'
export GRADE_URI='grades'

# Delete the existing Contact-Address entries and create a new entry
curl -sk -X DELETE ${API_BASE_URL}${CONTACT_ADDRESSES_URI}/all
export CONTACT_ADDR_ID=`curl -sk -X POST -H 'Content-Type: application/json' \
    -d '{"lineOne":"l1","lineTwo":"l2","lineThree":"l3","city":"New Delhi",\
    "state":"Delhi","country":"India","zipCode":"110001"}' \
    ${API_BASE_URL}${CONTACT_ADDRESSES_URI} | jq -r '.id'`
echo List of \'contact-addresses\' after creation
curl -sk ${API_BASE_URL}${CONTACT_ADDRESSES_URI} | jq -r '.content'

# Delete the existing Curriculum-entries and create a new entry
curl -sk -X DELETE ${API_BASE_URL}${CURRICULA_URI}/all
export CURRICULUM_ID=`curl -sk -X POST -H 'Content-Type: application/json' \
    -d '{"name":"CBSE","description":"Central Board of Secondary Education","board":"CBSE","pointOfContact":"Mr. Ankit Sharma, PHD",\
    "contactAddressId":"'"${CONTACT_ADDR_ID}"'"}'\
     ${API_BASE_URL}${CURRICULA_URI} | jq -r '.id'`
echo List of \'curricula\' after creation
curl -sk ${API_BASE_URL}${CURRICULA_URI} | jq -r '.content'

# Delete the existing Grade-entries and create a new entry
curl -sk -X DELETE ${API_BASE_URL}${GRADE_URI}/all
export GRADE_ID=`curl -sk -X POST -H 'Content-Type: application/json' \
    -d '{"name":"Grade-I","description":"Grade-I",\
    "curriculumId":"'"${CURRICULUM_ID}"'"}'\
    ${API_BASE_URL}${GRADE_URI}`
echo List of \'grades\' after creation
curl -sk ${API_BASE_URL}${GRADE_URI} | jq -r '.content'
