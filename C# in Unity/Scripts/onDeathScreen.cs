using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class onDeathScreen : MonoBehaviour
{
    Animator textAnim;

    // Start is called before the first frame update
    void Start()
    {
        textAnim = GetComponentInChildren<Animator>();
        textAnim.SetTrigger("gameOver");
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
