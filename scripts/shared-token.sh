#!/usr/bin/env bash
jwt encode --secret="testsharedsecret123" --alg "HS512" "$(cat <<EOF
{
  "iss": "shared-secret-issuer", 
  "sub": 1,
  "scopes": [ "scope" ],
  "roles": [ "role" ]
}
EOF
)"
