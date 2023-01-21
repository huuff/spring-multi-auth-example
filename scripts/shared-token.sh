#!/usr/bin/env bash
jwt encode --secret="testsharedsecret123" --alg "HS512" "$(cat <<EOF
{
  "iss": "shared-secret-issuer", 
  "sub": "e06b1e1a-1494-49cc-83bb-28ebae14a8a1",
  "scopes": [ "sample-scope" ],
  "roles": [ "sample-role" ]
}
EOF
)"
