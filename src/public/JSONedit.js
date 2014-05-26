function MainViewCtrl($scope, $filter) {

    // example JSON
 $scope.collection.jsonData = {
  "mdnId": 89,
  "shareMarketInd": "ctores legere me lius quod ii legunt saepius",
  "shareSynchInd": "nt lectores legere me lius",
  "expireDate": "cumsan et iusto odio dignissim",
  "effectiveDate": " haben",
  "rph": "gunt saepius. Clarita",
  "remark": "ore magna aliquam erat volutpat. U",
  "countryOfIssue": "zim placerat facer possim assum. Typ",
  "companyCardReference": "uetudium lectorum. Mir",
  "cardHolderRPH": "ud exerci tation ulla",
  "cardCode": "m, quis nostrud exerci tation ullamcorpe",
  "signatureOnFileInd": true,
  "secureInd": false,
  "extendedPaymentInd": false,
  "cardHolderName": " adipiscing e",
  "_id": "53751a6ff236f3020068210f",
  "__v": 0,
  "tpaExtensions": [
    {
      "_id": "53751a6ff236f302006820e9",
      "mdnId": 51,
      "any": [
        {
          "_id": "53751a6ff236f302006820e8",
          "id": "t, v",
          "mdnId": 50
        }
      ]
    }
  ],
  "signatureOnFile": [
    {
      "_id": "53751a6ff236f30200682103",
      "id": "ta nob",
      "mdnId": 77
    }
  ],
  "threeDomainSecurity": [
    {
      "_id": "53751a6ff236f30200682104",
      "id": "Lorem",
      "mdnId": 78
    }
  ],
  "magneticStripe": [
    {
      "_id": "53751a6ff236f30200682105",
      "plainText": "nt saepius. Claritas est etiam processus dy",
      "encryptionKey": "t facer possim assum. Typi non haben",
      "encryptionKeyMethod": "t lobortis nisl ut aliquip ex ea commod",
      "encryptionMethod": "um lectorum. Mirum est notare quam lit",
      "encryptedValue": "euismod tincidunt ut laoreet dolore magna aliqua",
      "mask": "d quod mazim placerat facer ",
      "token": ". Investigationes demonstraverunt lectores legere",
      "tokenProviderID": "uod ii legunt saepius. Clarit",
      "mdnId": 79,
      "tpaExtensions": [
        {
          "_id": "53751a6ff236f302006820e9",
          "mdnId": 51,
          "any": [
            {
              "_id": "53751a6ff236f302006820e8",
              "id": "t, v",
              "mdnId": 50
            }
          ]
        }
      ]
    }
  ],
  "custLoyalty": [
    {
      "_id": "53751a6ff236f30200682101",
      "id": "tur mutationem consuetudium lector",
      "mdnId": 75
    }
  ],
  "seriesCode": [
    {
      "_id": "53751a6ff236f30200682105",
      "plainText": "nt saepius. Claritas est etiam processus dy",
      "encryptionKey": "t facer possim assum. Typi non haben",
      "encryptionKeyMethod": "t lobortis nisl ut aliquip ex ea commod",
      "encryptionMethod": "um lectorum. Mirum est notare quam lit",
      "encryptedValue": "euismod tincidunt ut laoreet dolore magna aliqua",
      "mask": "d quod mazim placerat facer ",
      "token": ". Investigationes demonstraverunt lectores legere",
      "tokenProviderID": "uod ii legunt saepius. Clarit",
      "mdnId": 79,
      "tpaExtensions": [
        {
          "_id": "53751a6ff236f302006820e9",
          "mdnId": 51,
          "any": [
            {
              "_id": "53751a6ff236f302006820e8",
              "id": "t, v",
              "mdnId": 50
            }
          ]
        }
      ]
    }
  ],
  "cardNumber": [
    {
      "_id": "53751a6ff236f30200682105",
      "plainText": "nt saepius. Claritas est etiam processus dy",
      "encryptionKey": "t facer possim assum. Typi non haben",
      "encryptionKeyMethod": "t lobortis nisl ut aliquip ex ea commod",
      "encryptionMethod": "um lectorum. Mirum est notare quam lit",
      "encryptedValue": "euismod tincidunt ut laoreet dolore magna aliqua",
      "mask": "d quod mazim placerat facer ",
      "token": ". Investigationes demonstraverunt lectores legere",
      "tokenProviderID": "uod ii legunt saepius. Clarit",
      "mdnId": 79,
      "tpaExtensions": [
        {
          "_id": "53751a6ff236f302006820e9",
          "mdnId": 51,
          "any": [
            {
              "_id": "53751a6ff236f302006820e8",
              "id": "t, v",
              "mdnId": 50
            }
          ]
        }
      ]
    }
  ],
  "email": [
    {
      "_id": "53751a6ff236f302006820ea",
      "id": "luta nobis eleifend optio",
      "mdnId": 52
    }
  ],
  "telephone": [
    {
      "_id": "53751a6ff236f30200682106",
      "id": "consequat.",
      "mdnId": 80
    }
  ],
  "address": [
    {
      "_id": "53751a6ff236f3020068210b",
      "addressLine": "acer po",
      "cityName": "inim veniam, quis nostrud exerci tation ullamcor",
      "postalCode": "lla facilisi. Na",
      "county": "non h",
      "type": "ostrud exerci tation ullamcorper suscipit ",
      "remark": " lobortis nisl ut aliq",
      "language": " lectores legere me lius quod ii legunt saepiu",
      "validInd": true,
      "formattedInd": true,
      "shareSynchInd": "aritatem. Investigationes demonstraverunt lec",
      "shareMarketInd": " blandit praesent luptatum zzri",
      "mdnId": 85,
      "countryName": [
        {
          "_id": "53751a6ff236f30200682107",
          "value": "uis dolore te feugait nulla facil",
          "code": " facilisis at vero eros et accumsan et iusto od",
          "mdnId": 81
        }
      ],
      "stateProv": [
        {
          "_id": "53751a6ff236f30200682108",
          "value": "t",
          "stateCode": ", vel illum dolore eu feugiat nulla facilisis a",
          "mdnId": 82
        }
      ],
      "bldgRoom": [
        {
          "_id": "53751a6ff236f30200682109",
          "id": " dolor in hendrerit in vulputate velit",
          "mdnId": 83
        }
      ],
      "streetNmbr": [
        {
          "_id": "53751a6ff236f3020068210a",
          "id": " ea commodo consequ",
          "mdnId": 84
        }
      ]
    }
  ],
  "issuer": [
    {
      "_id": "53751a6ff236f3020068210c",
      "id": "us",
      "mdnId": 86
    }
  ],
  "cardType": [
    {
      "_id": "53751a6ff236f3020068210e",
      "removalInd": false,
      "code": "am nonummy nibh euismod tincidunt ut laoreet dolo",
      "description": "erat volutpat. Ut wisi enim ad min",
      "descriptionDetail": "gnissim qui blandit praesent lupt",
      "sourceName": "olor sit amet, consectetuer adipiscing elit,",
      "sourceURL": "facer possim assum. Typi non habent claritate",
      "resourceName": "ctorum. Mirum est n",
      "resourceURL": "facilisi. Nam liber",
      "uniqueID": "msa",
      "mdnId": 88,
      "value": [
        {
          "_id": "53751a6ff236f3020068210d",
          "value": " vulputate velit esse molestie conseq",
          "mdnId": 87
        }
      ]
    }
  ]
};

    $scope.$watch('jsonData', function(json) {
        $scope.jsonString = $filter('json')(json);
    }, true);
    $scope.$watch('jsonString', function(json) {
        try {
            $scope.collection.jsonData = JSON.parse(json);
            $scope.wellFormed = true;
        } catch(e) {
            $scope.wellFormed = false;
        }
    }, true);
}
