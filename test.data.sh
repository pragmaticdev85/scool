#/bin/sh
export API_BASE_URL='http://localhost:8080/api/'
export CONTACT_ADDRESSES_URI='contact-addresses'
export CURRICULA_URI='curricula'
curl -sk -X DELETE ${API_BASE_URL}${CONTACT_ADDRESSES_URI}/all
export CONTACT_ADDR_ID=`curl -sk -X POST -H 'Content-Type: application/json' \
    -d '{"lineOne":"l1","lineTwo":"l2","lineThree":"l3","city":"New York",\
    "state":"New York","country":"USA","zipCode":"87655"}' \
    ${API_BASE_URL}${CONTACT_ADDRESSES_URI} | jq -r '.id'`
echo List of contact-addresses after creation
curl -sk ${API_BASE_URL}${CONTACT_ADDRESSES_URI} | jq -r '.content'
echo ${CONTACT_ADDR_ID}
export CURRICULUM_ID=`curl -sk -X POST -H 'Content-Type: application/json' \
    -d '{"name":"n1","description":"d1","board":"b1","pointOfContact":"poc1",\
    "contactAddressId":"'"${CONTACT_ADDR_ID}"'"}'\
     ${API_BASE_URL}${CURRICULA_URI}`
echo ${CURRICULUM_ID}
