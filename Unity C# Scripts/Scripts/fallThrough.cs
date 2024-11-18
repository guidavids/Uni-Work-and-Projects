using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class fallThorugh : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        // Line of code so that enemies can go through each other
        Physics2D.IgnoreLayerCollision(LayerMask.NameToLayer("Attackable"), LayerMask.NameToLayer("Attackable"));
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
